package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "Staff")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Staff {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "hourlyRate")
    private double hourlyRate;

    @Column(name = "BirthDate")
    private LocalDate dateBirth;

    public Staff() {}

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
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
        this.hourlyRate = hourlyRate;
        this.dateBirth = DateBirth;
    }
}
