package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.repository.TheatreJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TheatreService {
    private final TheatreJpaRepository theatreRepo;

    public TheatreService(TheatreJpaRepository theatreRepo) {
        this.theatreRepo = theatreRepo;
    }

    @Transactional(readOnly = true)
    public List<Theatre> getAllTheatres() {
        List<Theatre> list = theatreRepo.findAll();
        for (Theatre t : list) {
            if (t.getHalls() != null) t.getHalls().size();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Optional<Theatre> getTheatreById(String id) {
        Optional<Theatre> opt = theatreRepo.findById(id);
        opt.ifPresent(t -> {
            if (t.getHalls() != null) t.getHalls().size();
        });
        return opt;
    }

    public Theatre addTheatre(Theatre theatre) {
        if (theatre.getId() == null || theatre.getId().isBlank()) {
            throw new IllegalArgumentException("ID is required and cannot be empty");
        }
        if (theatreRepo.existsById(theatre.getId())) {
            throw new IllegalArgumentException("A theatre with ID '" + theatre.getId() + "' already exists");
        }
        if (theatre.getName() == null || theatre.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (theatre.getCity() == null || theatre.getCity().isBlank()) {
            throw new IllegalArgumentException("City is required");
        }
        try {
            return theatreRepo.save(theatre);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Failed to save theatre: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save theatre: " + ex.getMessage(), ex);
        }
    }

    public void deleteTheatrebyId(String id) {
        theatreRepo.deleteById(id);
    }
}
