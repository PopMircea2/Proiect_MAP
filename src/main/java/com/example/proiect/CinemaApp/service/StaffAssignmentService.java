package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.repository.StaffAssignmentJpaRepository;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StaffAssignmentService {
    private final StaffAssignmentJpaRepository assignmentRepo;
    private final ScreeningJpaRepository screeningRepo;

    public StaffAssignmentService(StaffAssignmentJpaRepository assignmentRepo, ScreeningJpaRepository screeningRepo) {
        this.assignmentRepo = assignmentRepo;
        this.screeningRepo = screeningRepo;
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
        // resolve screening
        if (assignment.getScreeningId() != null && !assignment.getScreeningId().isBlank()) {
            Screening s = screeningRepo.findById(assignment.getScreeningId()).orElse(null);
            assignment.setScreening(s);
        }
        // resolve staff: find by id from Staff repository (Staff repo is represented by SupportStaffJpaRepository + TechnicalOperatorJpaRepository in this project)
        // a simple way: attempt to fetch via SupportStaff and TechnicalOperator repositories isn't necessary because Staff is the base entity and there is no StaffRepository; we'll rely on JPA cascading and that the Staff object is already managed when created elsewhere.
        return assignmentRepo.save(assignment);
    }

    public void deleteAssignmentbyId(String id) {
        assignmentRepo.deleteById(id);
    }
}
