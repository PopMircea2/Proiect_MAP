package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import java.util.*;

public class StaffAssignmentRepo extends InFileRepository<StaffAssignment>{
    protected StaffAssignmentRepo() {
        super(StaffAssignment::getId, StaffAssignment.class, "staffassignments.json");
    }
}
