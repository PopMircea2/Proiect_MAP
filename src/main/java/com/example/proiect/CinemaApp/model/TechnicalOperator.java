package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;

public class TechnicalOperator extends Staff{
    private TechnicalOperatorSpecialization specialization;

    public TechnicalOperator() { super(null, null, 0.0, null); }

    public TechnicalOperator(String id, String name, TechnicalOperatorSpecialization specialization, double hourlyRate, LocalDate DateBirth) {
        super(id, name,hourlyRate, DateBirth);
        this.specialization = specialization;
    }

    public TechnicalOperatorSpecialization getSpecialization() {
        return specialization;
    }
    public void setSpecialization(TechnicalOperatorSpecialization specialization) {
        this.specialization = specialization;
    }
}
