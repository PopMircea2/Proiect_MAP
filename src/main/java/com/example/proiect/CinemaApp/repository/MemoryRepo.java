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

public abstract class MemoryRepo<T> implements BaseRepo<T> {
    protected final Map<String, T> entities = new HashMap<>();
    private final Function<T, String> getId;
    private final Class<T> type;
    private final Path storagePath;
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT);

    protected MemoryRepo(Function<T, String> getId, Class<T> type, String storageFileName) {
        this.getId = getId;
        this.type = type;
        this.storagePath = Paths.get("data").resolve(storageFileName);
        try {
            Files.createDirectories(this.storagePath.getParent());
        } catch (IOException e) {
            throw new RuntimeException("Unable to create storage directory", e);
        }
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
        entities.put(id, entity);
        persist();
        return entity;
    }

    @Override
    public synchronized void deleteById(String id) {
        entities.remove(id);
        persist();
    }
}
