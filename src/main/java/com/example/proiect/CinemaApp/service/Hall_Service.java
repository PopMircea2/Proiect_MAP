package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.repository.Hall_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Hall_Service {
    private final Hall_Repo hallRepo;

    public Hall_Service(Hall_Repo hallRepo) {
        this.hallRepo = hallRepo;
    }

    public List<Hall> getAllHalls() {
        return hallRepo.findAll();
    }

    public Optional<Hall> getHallById(String id) {
        return hallRepo.findById(id);
    }

    public Hall addHall(Hall hall) {
        return hallRepo.save(hall);
    }

    public void deleteHall(String id) {
        hallRepo.deleteById(id);
    }
}
