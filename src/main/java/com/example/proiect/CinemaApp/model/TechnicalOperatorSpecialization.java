package com.example.proiect.CinemaApp.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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

    @Converter(autoApply = true)
    public static class TechnicalOperatorSpecializationConverter implements AttributeConverter<TechnicalOperatorSpecialization, String> {
        @Override
        public String convertToDatabaseColumn(TechnicalOperatorSpecialization attribute) {
            return attribute == null ? null : attribute.name();
        }

        @Override
        public TechnicalOperatorSpecialization convertToEntityAttribute(String dbData) {
            if (dbData == null) return null;
            try { return TechnicalOperatorSpecialization.valueOf(dbData); }
            catch (IllegalArgumentException ex) {
                for (TechnicalOperatorSpecialization s : TechnicalOperatorSpecialization.values()) {
                    if (s.name().equalsIgnoreCase(dbData) || s.displayName.equalsIgnoreCase(dbData)) return s;
                }
                throw ex;
            }
        }
    }

}
