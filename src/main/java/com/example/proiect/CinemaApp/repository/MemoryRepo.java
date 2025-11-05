package com.example.proiect.CinemaApp.repository;
import java.util.*;
import java.util.function.Function;

public abstract class MemoryRepo<T> implements BaseRepo<T> {
    protected final Map<String, T> entities = new HashMap<>();
    private final Function<T, String> getId;

    protected MemoryRepo(Function<T, String> getId) {
        this.getId = getId;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entities.values());
    }
    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(entities.get(id));
    }
    public T add(T entity) {
        String id = getId.apply(entity);
        entities.put(id, entity);
        return entity;
    }
    @Override
    public void deleteById(String id) {
        entities.remove(id);
    }
}
