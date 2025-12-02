package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.repository.SeatJpaRepository;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
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
        // validate required fields
        if (seat.getHall() == null) throw new IllegalArgumentException("Hall is required");
        if (seat.getRowLabel() == null || seat.getRowLabel().isBlank()) throw new IllegalArgumentException("Row label is required");
        if (seat.getColumnNumber() <= 0) throw new IllegalArgumentException("Column number must be positive");
        try {
            return seatRepo.save(seat);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Failed to save seat: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save seat: " + ex.getMessage(), ex);
        }
    }

    public void deleteSeatbyId(String id) {
        seatRepo.deleteById(id);
    }
}
