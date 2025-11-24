package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.repository.SeatJpaRepository;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeatService {
    private final SeatJpaRepository seatRepo;
    private final HallJpaRepository hallRepo;

    public SeatService(SeatJpaRepository seatRepo, HallJpaRepository hallRepo) {
        this.seatRepo = seatRepo;
        this.hallRepo = hallRepo;
    }

    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    public Optional<Seat> getSeatById(String id) {
        return seatRepo.findById(id);
    }

    public Seat addSeat(Seat seat) {
        if (seat.getId() == null || seat.getId().isBlank()) {
            seat.setId(UUID.randomUUID().toString());
        }
        // Resolve hall relation from transient hallId if provided
        String hallId = seat.getHallId();
        if (hallId != null && !hallId.isBlank()) {
            Hall h = hallRepo.findById(hallId).orElse(null);
            seat.setHall(h);
        }
        return seatRepo.save(seat);
    }

    public void deleteSeatbyId(String id) {
        seatRepo.deleteById(id);
    }
}
