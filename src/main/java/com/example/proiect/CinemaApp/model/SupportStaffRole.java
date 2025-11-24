package com.example.proiect.CinemaApp.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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

    // Converter nested to avoid creating a separate file. autoApply=true makes JPA use it automatically.
    @Converter(autoApply = true)
    public static class SupportStaffRoleConverter implements AttributeConverter<SupportStaffRole, String> {
        @Override
        public String convertToDatabaseColumn(SupportStaffRole attribute) {
            return attribute == null ? null : attribute.name();
        }

        @Override
        public SupportStaffRole convertToEntityAttribute(String dbData) {
            if (dbData == null) return null;
            // try direct match to enum name first
            try {
                return SupportStaffRole.valueOf(dbData);
            } catch (IllegalArgumentException ex) {
                // try case-insensitive name match or displayName match
                for (SupportStaffRole r : SupportStaffRole.values()) {
                    if (r.name().equalsIgnoreCase(dbData) || r.displayName.equalsIgnoreCase(dbData)) {
                        return r;
                    }
                }
                // rethrow original exception to keep behaviour if truly unknown
                throw ex;
            }
        }
    }

}
