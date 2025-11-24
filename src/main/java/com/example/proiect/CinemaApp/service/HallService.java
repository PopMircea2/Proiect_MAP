package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    private final HallJpaRepository hallRepo;

    public HallService(HallJpaRepository hallRepo) {
        this.hallRepo = hallRepo;
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
        return hallRepo.save(hall);
    }

    public void deleteHallbyId(String id) {
        hallRepo.deleteById(id);
    }
}
