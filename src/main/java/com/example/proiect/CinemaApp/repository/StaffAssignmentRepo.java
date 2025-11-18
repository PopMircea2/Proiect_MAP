package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StaffAssignmentRepo extends InFileRepository<StaffAssignment>{
    protected StaffAssignmentRepo() {
        super(StaffAssignment::getId, StaffAssignment.class, "staffassignments.json");
    }
}
