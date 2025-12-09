package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import com.example.proiect.CinemaApp.repository.TheatreJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    private final HallJpaRepository hallRepo;
    private final TheatreJpaRepository theatreRepo;

    public HallService(HallJpaRepository hallRepo, @Nullable TheatreJpaRepository theatreRepo) {
        this.hallRepo = hallRepo;
        this.theatreRepo = theatreRepo;
    }

    @Transactional(readOnly = true)
    public List<Hall> getAllHalls() {
        List<Hall> halls = hallRepo.findAll();
        for (Hall h : halls) {
            if (h.getTheatre() != null) h.getTheatre().getName();
            if (h.getSeats() != null) h.getSeats().size();
            if (h.getScreenings() != null) h.getScreenings().size();
        }
        return halls;
    }

    @Transactional(readOnly = true)
    public Optional<Hall> getHallById(String id) {
        Optional<Hall> opt = hallRepo.findById(id);
        opt.ifPresent(h -> {
            if (h.getTheatre() != null) h.getTheatre().getName();
            if (h.getSeats() != null) h.getSeats().size();
            if (h.getScreenings() != null) h.getScreenings().size();
        });
        return opt;
    }

    public Hall addHall(Hall hall) {
        if (hall.getId() == null || hall.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (hallRepo.existsById(hall.getId())) {
            throw new BusinessValidationException("A hall with ID '" + hall.getId() + "' already exists");
        }
        return VerifyHall(hall);
    }

    public Hall updateHall(Hall hall) {
        if (hall.getId() == null || hall.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (!hallRepo.existsById(hall.getId())) {
            throw new BusinessValidationException("Hall with ID '" + hall.getId() + "' does not exist");
        }
        return VerifyHall(hall);
    }

    private Hall VerifyHall(Hall hall) {
        String theatreId = hall.getTheatreId(); // Assumes getter exists in Hall.java
        if (theatreId != null && !theatreId.isBlank()) {
            if (theatreRepo == null) {
                throw new BusinessValidationException("Theatre repository is not initialized");
            }
            Theatre t = theatreRepo.findById(theatreId)
                    .orElseThrow(() -> new BusinessValidationException("Theatre with ID '" + theatreId + "' does not exist"));
            hall.setTheatre(t);
        }
        if (hall.getName() == null || hall.getName().isBlank()) {
            throw new BusinessValidationException("Name is required");
        }
        if (hall.getTheatre() == null) {
            throw new BusinessValidationException("Theatre is required");
        }
        if (hall.getCapacity() <= 0) {
            throw new BusinessValidationException("Capacity must be positive");
        }

        try {
            return hallRepo.save(hall);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save hall: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save hall: " + ex.getMessage(), ex);
        }
    }

    public void deleteHallbyId(String id) {
        hallRepo.deleteById(id);
    }
}