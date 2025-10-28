package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Screening;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Screening_Repo{
    private final Map<String, Screening> screenings = new HashMap<>();

    public List<Screening> findAll() {
        return new ArrayList<>(screenings.values());
    }

    public Optional<Screening> findById(String id) {
        return Optional.ofNullable(screenings.get(id));
    }

    public Screening save(Screening screening) {
        screenings.put(screening.getId(), screening);
        return screening;
    }

    public void deleteById(String id) {
        screenings.remove(id);
    }
}

