package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StaffAssignmentRepo extends MemoryRepo<StaffAssignment>{
    protected StaffAssignmentRepo() {
        super(StaffAssignment::getId);
    }
}

