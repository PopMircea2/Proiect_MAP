package com.example.proiect.CinemaApp.model;

public class SupportStaff extends Staff {
    private String role;

    public SupportStaff(String id, String name) {
        super(id, name);
    }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
