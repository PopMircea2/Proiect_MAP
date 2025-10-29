package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Theatre;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TheatreRepo extends MemoryRepo<Theatre>{
    protected TheatreRepo() {
        super(Theatre::getId);
    }
}

