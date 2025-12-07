package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import com.example.proiect.CinemaApp.repository.TechnicalOperatorJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
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
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (technicalOperatorRepo.existsById(technicalOperator.getId())) {
            throw new BusinessValidationException("Customer with ID '" + technicalOperator.getId() + "' does not exist");
        }
        return VerifyTechnicalOperator(technicalOperator);
    }

    private TechnicalOperator VerifyTechnicalOperator(TechnicalOperator technicalOperator) {
        if (technicalOperator.getStaffType() == null || technicalOperator.getStaffType().isBlank()) {
            technicalOperator.setStaffType(technicalOperator.getClass().getSimpleName());
        }

        // validate required fields
        if (technicalOperator.getDateBirth() == null) {
            throw new BusinessValidationException("Birth date is required");
        }
        if (technicalOperator.getName() == null || technicalOperator.getName().isBlank()) {
            throw new BusinessValidationException("Name is required");
        }
        if (technicalOperator.getHourlyRate() <= 0.0) {
            throw new BusinessValidationException("Hourly rate must be positive");
        }
        if (technicalOperator.getSpecialization() == null) {
            throw new BusinessValidationException("Specialization is required");
        }

        try {
            return technicalOperatorRepo.save(technicalOperator);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save technical operator: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save technical operator: " + ex.getMessage(), ex);
        }
    }
    public TechnicalOperator updateTechnicalOperator(TechnicalOperator technicalOperator) {
        // For update, ID must exist and not be blank
        if (technicalOperator.getId() == null || technicalOperator.getId().isBlank()) {
            throw new BusinessValidationException("ID is required for update");
        }
        if (!technicalOperatorRepo.existsById(technicalOperator.getId())) {
            throw new BusinessValidationException("Technical operator with ID '" + technicalOperator.getId() + "' does not exist");
        }
        return VerifyTechnicalOperator(technicalOperator);
    }

    public void deleteTechnicalOperatorbyId(String id) {
        technicalOperatorRepo.deleteById(id);
    }
}
