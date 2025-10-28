package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.repository.Seat_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Seat_Service {
    private final Seat_Repo seatRepo;

    public Seat_Service(Seat_Repo seatRepo) {
        this.seatRepo = seatRepo;
    }

    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    public Optional<Seat> getSeatById(String id) {
        return seatRepo.findById(id);
    }

    public Seat addSeat(Seat seat) {
        return seatRepo.save(seat);
    }

    public void deleteSeat(String id) {
        seatRepo.deleteById(id);
    }
}
