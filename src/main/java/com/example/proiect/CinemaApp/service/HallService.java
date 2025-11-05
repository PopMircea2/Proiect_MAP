package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.repository.HallRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    private final HallRepo hallRepo;

    public HallService(HallRepo hallRepo) {
        this.hallRepo = hallRepo;
    }

    public List<Hall> getAllHalls() {
        return hallRepo.findAll();
    }

    public Optional<Hall> getHallById(String id) {
        return hallRepo.findById(id);
    }

    public Hall addHall(Hall hall) {
        return hallRepo.add(hall);
    }

    public void deleteHallbyId(String id) {
        hallRepo.deleteById(id);
    }
}
