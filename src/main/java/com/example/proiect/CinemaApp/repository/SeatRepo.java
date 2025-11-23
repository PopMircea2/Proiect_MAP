package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Seat;
import java.util.*;

public class SeatRepo extends InFileRepository<Seat> {
    protected SeatRepo() {
        super(Seat::getId, Seat.class, "seats.json");
    }
}
