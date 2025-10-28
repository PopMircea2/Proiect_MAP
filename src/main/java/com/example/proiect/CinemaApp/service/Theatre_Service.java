package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.repository.Theatre_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Theatre_Service {
    private final Theatre_Repo theatreRepo;

    public Theatre_Service(Theatre_Repo theatreRepo) {
        this.theatreRepo = theatreRepo;
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepo.findAll();
    }

    public Optional<Theatre> getTheatreById(String id) {
        return theatreRepo.findById(id);
    }

    public Theatre addTheatre(Theatre theatre) {
        return theatreRepo.save(theatre);
    }

    public void deleteTheatre(String id) {
        theatreRepo.deleteById(id);
    }
}
