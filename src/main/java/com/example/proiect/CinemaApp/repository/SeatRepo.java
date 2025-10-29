package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Seat;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class SeatRepo extends MemoryRepo<Seat> {
    protected SeatRepo() {
        super(Seat::getId);
    }
}

