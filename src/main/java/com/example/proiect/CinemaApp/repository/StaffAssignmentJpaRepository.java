package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffAssignmentJpaRepository extends JpaRepository<StaffAssignment, String>, JpaSpecificationExecutor<StaffAssignment> {
}
