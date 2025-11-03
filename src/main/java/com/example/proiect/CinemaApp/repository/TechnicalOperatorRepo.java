package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class TechnicalOperatorRepo extends MemoryRepo<Staff>{
    protected TechnicalOperatorRepo(){
        super(Staff::getId);
    }
}
