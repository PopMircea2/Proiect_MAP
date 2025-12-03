package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.SupportStaff;
import com.example.proiect.CinemaApp.repository.SupportStaffJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupportStaffService {
    private final SupportStaffJpaRepository supportStaffRepo;

    public SupportStaffService(SupportStaffJpaRepository supportStaffRepo) {
        this.supportStaffRepo = supportStaffRepo;
    }

    public List<SupportStaff> getAllSupportStaff() {
        return supportStaffRepo.findAll();
    }

    public Optional<SupportStaff> getSupportStaffById(String id) {
        return supportStaffRepo.findById(id);
    }

    public SupportStaff addSupportStaff(SupportStaff staff) {
        // ensure id
        if (staff.getId() == null || staff.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (supportStaffRepo.existsById(staff.getId())) {
            throw new BusinessValidationException("SupportSatff with ID '" + staff.getId() + "' already exists");
        }
        // ensure staffType column is set (defensive, because DB requires it)
        if (staff.getStaffType() == null || staff.getStaffType().isBlank()) {
            staff.setStaffType(staff.getClass().getSimpleName());
        }
        // validate required fields before attempting save
        if (staff.getDateBirth() == null) {
            throw new BusinessValidationException("Birth date is required");
        }
        if (staff.getName() == null || staff.getName().isBlank()) {
            throw new BusinessValidationException("Name is required");
        }
        if (staff.getHourlyRate() <= 0.0) {
            throw new BusinessValidationException("Hourly rate must be positive");
        }
        if (staff.getRole() == null) {
            throw new BusinessValidationException("Role is required");
        }

        try {
            return supportStaffRepo.save(staff);
        } catch (DataIntegrityViolationException ex) {
            // wrap to surface clearer message to controllers/views
            throw new BusinessValidationException("Failed to save support staff: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save support staff: " + ex.getMessage(), ex);
        }
    }

    public SupportStaff updateSupportStaff(SupportStaff staff) {
        // For update, ID must exist and not be blank
        if (staff.getId() == null || staff.getId().isBlank()) {
            throw new BusinessValidationException("ID is required for update");
        }
        if (!supportStaffRepo.existsById(staff.getId())) {
            throw new BusinessValidationException("Support staff with ID '" + staff.getId() + "' does not exist");
        }
        // ensure staffType column is set (defensive, because DB requires it)
        if (staff.getStaffType() == null || staff.getStaffType().isBlank()) {
            staff.setStaffType(staff.getClass().getSimpleName());
        }
        // validate required fields before attempting save
        if (staff.getDateBirth() == null) {
            throw new BusinessValidationException("Birth date is required");
        }
        if (staff.getName() == null || staff.getName().isBlank()) {
            throw new BusinessValidationException("Name is required");
        }
        if (staff.getHourlyRate() <= 0.0) {
            throw new BusinessValidationException("Hourly rate must be positive");
        }
        if (staff.getRole() == null) {
            throw new BusinessValidationException("Role is required");
        }

        try {
            return supportStaffRepo.save(staff);
        } catch (DataIntegrityViolationException ex) {
            // wrap to surface clearer message to controllers/views
            throw new BusinessValidationException("Failed to save support staff: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save support staff: " + ex.getMessage(), ex);
        }
    }

    public void deleteSupportStaffbyId(String id) {
        supportStaffRepo.deleteById(id);
    }
}
