package com.example.proiect.CinemaApp.repository;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T> {
    List<T> findAll();
    Optional<T> findById(String id);
    T add(T entity);
    void deleteById(String id);
}

