package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Seat;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class SeatRepo {
    private final Map<String, Seat> seats = new HashMap<>();

    public List<Seat> findAll() {
        return new ArrayList<>(seats.values());
    }

    public Optional<Seat> findById(String id) {
        return Optional.ofNullable(seats.get(id));
    }

    public Seat save(Seat seat) {
        seats.put(seat.getId(), seat);
        return seat;
    }

    public void deleteById(String id) {
        seats.remove(id);
    }
}

