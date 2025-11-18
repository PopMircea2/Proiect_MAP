package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Hall;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class HallRepo extends InFileRepository<Hall>{
    protected HallRepo() {
        super(Hall::getId, Hall.class, "halls.json");
    }
}
