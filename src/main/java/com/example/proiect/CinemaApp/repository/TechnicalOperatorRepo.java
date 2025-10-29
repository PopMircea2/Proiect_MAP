package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Staff;
import com.example.proiect.CinemaApp.model.TechnicalOperator;

public class TechnicalOperatorRepo extends MemoryRepo<Staff>{
    protected TechnicalOperatorRepo(){
        super(Staff::getId);
    }
}
