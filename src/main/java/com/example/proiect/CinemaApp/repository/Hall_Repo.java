package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Hall;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Hall_Repo {
    private final Map<String, Hall> halls = new HashMap<>();

    public List<Hall> findAll() {
        return new ArrayList<>(halls.values());
    }

    public Optional<Hall> findById(String id) {
        return Optional.ofNullable(halls.get(id));
    }

    public Hall save(Hall hall) {
        halls.put(hall.getId(), hall);
        return hall;
    }

    public void deleteById(String id) {
        halls.remove(id);
    }
}
