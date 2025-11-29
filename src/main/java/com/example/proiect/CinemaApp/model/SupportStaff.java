package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SupportStaff")
@PrimaryKeyJoinColumn(name = "StaffId", referencedColumnName = "Id")
public class SupportStaff extends Staff {
    @NotNull(message = "Role is required")
    @Column(name = "Role", columnDefinition = "VARCHAR(50)")
    private SupportStaffRole role;

    public SupportStaff() { super(null, null, 0.0, null); }

    public SupportStaff(String id, String name, SupportStaffRole role, double hourlyRate, LocalDate DateBirth) {
        super(id, name,hourlyRate, DateBirth);
        this.role = role;
    }
    public SupportStaffRole getRole() { return role; }
    public void setRole(SupportStaffRole role) { this.role = role; }
}
