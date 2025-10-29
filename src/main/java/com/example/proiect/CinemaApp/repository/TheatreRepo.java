package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Theatre;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TheatreRepo {
    private final Map<String, Theatre> theatres = new HashMap<>();

    public List<Theatre> findAll() {
        return new ArrayList<>(theatres.values());
    }

    public Optional<Theatre> findById(String id) {
        return Optional.ofNullable(theatres.get(id));
    }

    public Theatre save(Theatre theatre) {
        theatres.put(theatre.getId(), theatre);
        return theatre;
    }

    public void deleteById(String id) {
        theatres.remove(id);
    }
}

