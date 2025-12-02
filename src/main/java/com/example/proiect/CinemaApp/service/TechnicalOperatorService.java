package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import com.example.proiect.CinemaApp.repository.TechnicalOperatorJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TechnicalOperatorService {
    private final TechnicalOperatorJpaRepository technicalOperatorRepo;

    public TechnicalOperatorService(TechnicalOperatorJpaRepository technicalOperatorRepo) {
        this.technicalOperatorRepo = technicalOperatorRepo;
    }

    public List<TechnicalOperator> getAllTechnicalOperators() {
        return technicalOperatorRepo.findAll();
    }

    public Optional<TechnicalOperator> getTechnicalOperatorById(String id) {
        return technicalOperatorRepo.findById(id);
    }

    public TechnicalOperator addTechnicalOperator(TechnicalOperator technicalOperator) {
        // ensure id
        if (technicalOperator.getId() == null || technicalOperator.getId().isBlank()) {
            technicalOperator.setId(UUID.randomUUID().toString());
        }
        // ensure staffType
        if (technicalOperator.getStaffType() == null || technicalOperator.getStaffType().isBlank()) {
            technicalOperator.setStaffType(technicalOperator.getClass().getSimpleName());
        }

        // validate required fields
        if (technicalOperator.getDateBirth() == null) {
            throw new IllegalArgumentException("Birth date is required");
        }
        if (technicalOperator.getName() == null || technicalOperator.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (technicalOperator.getHourlyRate() <= 0.0) {
            throw new IllegalArgumentException("Hourly rate must be positive");
        }
        if (technicalOperator.getSpecialization() == null) {
            throw new IllegalArgumentException("Specialization is required");
        }

        try {
            return technicalOperatorRepo.save(technicalOperator);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Failed to save technical operator: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save technical operator: " + ex.getMessage(), ex);
        }
    }

    public void deleteTechnicalOperatorbyId(String id) {
        technicalOperatorRepo.deleteById(id);
    }
}
