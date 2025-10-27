package com.example.proiect.CinemaApp.model;

public class TechnicalOperator extends Staff{
    private String specialization;

    public TechnicalOperator(String id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
