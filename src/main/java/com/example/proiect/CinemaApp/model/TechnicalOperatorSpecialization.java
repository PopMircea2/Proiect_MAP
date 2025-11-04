package com.example.proiect.CinemaApp.model;

public enum TechnicalOperatorSpecialization {
    PROJECTION("Projection"),
    SOUND("Sound");

    private final String displayName;

    TechnicalOperatorSpecialization(String displayName) {
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
