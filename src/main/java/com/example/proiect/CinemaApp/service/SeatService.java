package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.repository.SeatJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SeatService {
    private final SeatJpaRepository seatRepo;

    public SeatService(SeatJpaRepository seatRepo) {
        this.seatRepo = seatRepo;
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
        return seatRepo.save(seat);
    }

    public void deleteSeatbyId(String id) {
        seatRepo.deleteById(id);
    }
}
