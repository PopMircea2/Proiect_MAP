package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;

public class TechnicalOperator extends Staff{
    private String specialization;

    public TechnicalOperator() { super(null, null, 0.0, null); }

    public TechnicalOperator(String id, String name, String specialization, double hourlyRate, LocalDate DateBirth) {
        super(id, name,hourlyRate, DateBirth);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
