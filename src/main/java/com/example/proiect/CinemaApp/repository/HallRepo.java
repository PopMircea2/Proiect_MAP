package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Hall;
import java.util.*;

public class HallRepo extends InFileRepository<Hall>{
    protected HallRepo() {
        super(Hall::getId, Hall.class, "halls.json");
    }
}
