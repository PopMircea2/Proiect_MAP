package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Hall;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class HallRepo extends MemoryRepo<Hall>{
    protected HallRepo() {
        super(Hall::getId);
    }
}
