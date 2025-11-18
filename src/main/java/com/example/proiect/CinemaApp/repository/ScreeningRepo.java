package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Screening;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ScreeningRepo extends InFileRepository<Screening>{
    protected ScreeningRepo() {
        super(Screening::getId, Screening.class, "screenings.json");
    }

}
