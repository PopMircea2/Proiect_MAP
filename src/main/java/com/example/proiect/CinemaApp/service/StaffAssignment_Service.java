package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import com.example.proiect.CinemaApp.repository.StaffAssignment_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffAssignment_Service {
    private final StaffAssignment_Repo assignmentRepo;

    public StaffAssignment_Service(StaffAssignment_Repo assignmentRepo) {
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

    public void deleteAssignment(String id) {
        assignmentRepo.deleteById(id);
    }
}
