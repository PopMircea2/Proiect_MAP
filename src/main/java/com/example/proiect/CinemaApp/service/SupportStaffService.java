package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.model.SupportStaff;
import com.example.proiect.CinemaApp.repository.SupportStaffJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class SupportStaffService {
    private final SupportStaffJpaRepository supportStaffRepo;

    public SupportStaffService(SupportStaffJpaRepository supportStaffRepo) {
        this.supportStaffRepo = supportStaffRepo;
    }

    public List<SupportStaff> getAllSupportStaff() {
        return supportStaffRepo.findAll();
    }

    // New: filterable + sortable
    public List<SupportStaff> getAllSupportStaff(String q, String role, String sortBy, String sortDir) {
        Sort sort = Sort.by((sortBy == null || sortBy.isBlank()) ? "id" : sortBy);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Specification<SupportStaff> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (q != null && !q.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + q.toLowerCase() + "%"));
            }
            if (role != null && !role.isBlank()) {
                // compare by enum name or string representation
                predicates.add(cb.equal(root.get("role").as(String.class), role));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return supportStaffRepo.findAll(spec, sort);
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
        return VerifySupportStaff(staff);

    }

    private SupportStaff VerifySupportStaff(SupportStaff staff){
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
        return  VerifySupportStaff(staff);

    }

    public void deleteSupportStaffbyId(String id) {
        supportStaffRepo.deleteById(id);
    }
}
