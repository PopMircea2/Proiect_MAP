package com.example.proiect.CinemaApp.repository;

import java.util.*;
import java.util.function.Function;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

public abstract class InFileRepository<T> implements AbstractRepository<T> {
    protected final Map<String, T> entities = new HashMap<>();
    private final Function<T, String> getId;
    private final Class<T> type;
    private final Path storagePath;
    private final Path resourcePath;
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT);

    protected InFileRepository(Function<T, String> getId, Class<T> type, String storageFileName) {
        this.getId = getId;
        this.type = type;
        // Use resources/data/ for initial files, but write to external data/ at runtime
        Path resourcesData = Paths.get("src/main/resources/data");
        Path resourceFile = resourcesData.resolve(storageFileName);
        Path runtimeDataDir = Paths.get("data");
        Path runtimeFile = runtimeDataDir.resolve(storageFileName);
        this.resourcePath = resourceFile;
        try {
            Files.createDirectories(runtimeDataDir);
            // If runtime file doesn't exist but resource file exists, copy initial data
            if (!Files.exists(runtimeFile) && Files.exists(resourceFile)) {
                Files.copy(resourceFile, runtimeFile);
            }
            // If runtime file exists but is empty, prefer resource file if available
            if (Files.exists(runtimeFile)) {
                try {
                    long size = Files.size(runtimeFile);
                    if (size == 0 && Files.exists(resourceFile)) {
                        Files.copy(resourceFile, runtimeFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException ex) {
                    // ignore size check issues
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to prepare storage file", e);
        }
        this.storagePath = runtimeFile;
        loadFromFile();
    }

    private void loadFromFile() {
        if (Files.exists(storagePath)) {
            try {
                CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
                List<T> list = mapper.readValue(storagePath.toFile(), listType);
                for (T e : list) {
                    String id = getId.apply(e);
                    if (id != null) {
                        entities.put(id, e);
                    }
                }
            } catch (com.fasterxml.jackson.databind.exc.MismatchedInputException mie) {
                // File exists but empty or malformed; try to recover by using resource file if available
                if (resourcePath != null && Files.exists(resourcePath)) {
                    try {
                        Files.copy(resourcePath, storagePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                        // retry loading once
                        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, type);
                        List<T> list = mapper.readValue(storagePath.toFile(), listType);
                        for (T e : list) {
                            String id = getId.apply(e);
                            if (id != null) {
                                entities.put(id, e);
                            }
                        }
                    } catch (IOException ex) {
                        // give up and throw
                        throw new RuntimeException("Failed to read storage file after recovering from empty content: " + storagePath, ex);
                    }
                } else {
                    // no resource fallback; treat as empty dataset
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to read storage file: " + storagePath, e);
            }
        }
    }

    private synchronized void persist() {
        try {
            List<T> list = new ArrayList<>(entities.values());
            mapper.writeValue(storagePath.toFile(), list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write storage file: " + storagePath, e);
        }
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public synchronized T add(T entity) {
        String id = getId.apply(entity);
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Entity id must be provided and non-blank");
        }
        if (entities.containsKey(id)) {
            // Replace existing (behave like previous implementation)
            entities.put(id, entity);
        } else {
            entities.put(id, entity);
        }
        persist();
        return entity;
    }

    @Override
    public synchronized void deleteById(String id) {
        entities.remove(id);
        persist();
    }
}

