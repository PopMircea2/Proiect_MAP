package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StaffAssignment_Repo{
    private final Map<String, StaffAssignment> assignments = new HashMap<>();

    public List<StaffAssignment> findAll() {
        return new ArrayList<>(assignments.values());
    }

    public Optional<StaffAssignment> findById(String id) {
        return Optional.ofNullable(assignments.get(id));
    }

    public StaffAssignment save(StaffAssignment assignment) {
        assignments.put(assignment.getId(), assignment);
        return assignment;
    }

    public void deleteById(String id) {
        assignments.remove(id);
    }
}

