package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import com.example.proiect.CinemaApp.repository.StaffAssignmentJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffAssignmentService {
    private final StaffAssignmentJpaRepository assignmentRepo;

    public StaffAssignmentService(StaffAssignmentJpaRepository assignmentRepo) {
        this.assignmentRepo = assignmentRepo;
    }

    public List<StaffAssignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public Optional<StaffAssignment> getAssignmentById(String id) {
        return assignmentRepo.findById(id);
    }

    public StaffAssignment addAssignment(StaffAssignment assignment) {
        return assignmentRepo.save(assignment);
    }

    public void deleteAssignmentbyId(String id) {
        assignmentRepo.deleteById(id);
    }
}
