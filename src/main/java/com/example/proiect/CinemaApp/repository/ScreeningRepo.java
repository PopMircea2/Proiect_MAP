package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Screening;
import java.util.*;

public class ScreeningRepo extends InFileRepository<Screening>{
    protected ScreeningRepo() {
        super(Screening::getId, Screening.class, "screenings.json");
    }
}
