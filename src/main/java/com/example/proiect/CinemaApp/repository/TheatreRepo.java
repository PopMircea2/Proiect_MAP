package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Theatre;
import java.util.*;

public class TheatreRepo extends InFileRepository<Theatre>{
    protected TheatreRepo() {
        super(Theatre::getId, Theatre.class, "theatres.json");
    }
}
