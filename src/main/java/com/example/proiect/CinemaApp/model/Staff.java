package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;

public abstract class Staff {
    private String id;
    private String name;
    private double HourlyRate;
    private LocalDate DateBirth;

    public LocalDate getDateBirth() {
        return DateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.DateBirth = dateBirth;
    }

    public double getHourlyRate() {
        return HourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        HourlyRate = hourlyRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Staff(String id, String name,double hourlyRate, LocalDate DateBirth) {
        this.id = id;
        this.name = name;
        this.HourlyRate = hourlyRate;
        this.DateBirth = DateBirth;
    }
}
