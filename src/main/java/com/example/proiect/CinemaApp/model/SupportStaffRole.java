package com.example.proiect.CinemaApp.model;

public enum SupportStaffRole {
    USHER("Usher"),
    CLEANING("Cleaning"),
    SECURITY("Security");

    private final String displayName;

    SupportStaffRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
