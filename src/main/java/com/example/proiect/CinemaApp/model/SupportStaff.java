package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;

public class SupportStaff extends Staff {
    private SupportStaffRole role;

    public SupportStaff() { super(null, null, 0.0, null); }

    public SupportStaff(String id, String name, double hourlyRate, LocalDate DateBirth) {
        super(id, name,hourlyRate, DateBirth);
    }
    public SupportStaffRole getRole() { return role; }
    public void setRole(SupportStaffRole role) { this.role = role; }
}
