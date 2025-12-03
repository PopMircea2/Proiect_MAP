package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Staff")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Staff {
    @Id
    @Column(name = "Id")
    private String id;

    //@NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name = "Name")
    private String name;

    @Positive(message = "Hourly rate must be positive")
    @Column(name = "hourlyRate")
    private double hourlyRate;

    //@NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BirthDate")
    private LocalDate dateBirth;

    @Column(name = "StaffType", length = 50)
    private String staffType;

    public Staff() {}

    @PrePersist
    @PreUpdate
    private void ensureStaffType() {
        if (this.staffType == null || this.staffType.isBlank()) {
            // Map concrete classes to the short DB values used in data.sql (lowercase)
            String cls = this.getClass().getSimpleName();
            if ("SupportStaff".equals(cls)) this.staffType = "support";
            else if ("TechnicalOperator".equals(cls)) this.staffType = "technical";
            else this.staffType = cls.toLowerCase();
        }
    }

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

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }
}
