package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.SupportStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportStaffJpaRepository extends JpaRepository<SupportStaff, String>, JpaSpecificationExecutor<SupportStaff> {
}
