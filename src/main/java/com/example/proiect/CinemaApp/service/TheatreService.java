package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.repository.TheatreJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {
    private final TheatreJpaRepository theatreRepo;

    public TheatreService(TheatreJpaRepository theatreRepo) {
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

    public void deleteTheatrebyId(String id) {
        theatreRepo.deleteById(id);
    }
}
