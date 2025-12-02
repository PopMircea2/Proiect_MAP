package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.repository.StaffAssignmentJpaRepository;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffAssignmentService {
    private final StaffAssignmentJpaRepository assignmentRepo;
    private final ScreeningJpaRepository screeningRepo;
    private final SupportStaffService supportStaffService;
    private final TechnicalOperatorService technicalOperatorService;

    public StaffAssignmentService(StaffAssignmentJpaRepository assignmentRepo, ScreeningJpaRepository screeningRepo,
                                  SupportStaffService supportStaffService, TechnicalOperatorService technicalOperatorService) {
        this.assignmentRepo = assignmentRepo;
        this.screeningRepo = screeningRepo;
        this.supportStaffService = supportStaffService;
        this.technicalOperatorService = technicalOperatorService;
    }

    @Transactional(readOnly = true)
    public List<StaffAssignment> getAllAssignments() {
        List<StaffAssignment> list = assignmentRepo.findAll();
        for (StaffAssignment s : list) {
            if (s.getScreening() != null) s.getScreening().getId();
            if (s.getStaff() != null) s.getStaff().getName();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Optional<StaffAssignment> getAssignmentById(String id) {
        Optional<StaffAssignment> opt = assignmentRepo.findById(id);
        opt.ifPresent(a -> {
            if (a.getScreening() != null) a.getScreening().getId();
            if (a.getStaff() != null) a.getStaff().getName();
        });
        return opt;
    }

    public StaffAssignment addAssignment(StaffAssignment assignment) {
        if (assignment.getId() == null || assignment.getId().isBlank()) assignment.setId(UUID.randomUUID().toString());

        // resolve screening
        if (assignment.getScreeningId() != null && !assignment.getScreeningId().isBlank()) {
            Screening s = screeningRepo.findById(assignment.getScreeningId()).orElse(null);
            assignment.setScreening(s);
        }
        // resolve staff: try support staff first, then technical operators
        if (assignment.getStaffId() != null && !assignment.getStaffId().isBlank()) {
            String sid = assignment.getStaffId();
            // try support staff
            supportStaffService.getSupportStaffById(sid).ifPresentOrElse(
                    st -> assignment.setStaff(st),
                    () -> technicalOperatorService.getTechnicalOperatorById(sid).ifPresent(assignment::setStaff)
            );
        }

        if (assignment.getScreening() == null) throw new IllegalArgumentException("Screening is required");
        if (assignment.getStaff() == null) throw new IllegalArgumentException("Staff is required");

        try {
            return assignmentRepo.save(assignment);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Failed to save assignment: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save assignment: " + ex.getMessage(), ex);
        }
    }

    public void deleteAssignmentbyId(String id) {
        assignmentRepo.deleteById(id);
    }
}
