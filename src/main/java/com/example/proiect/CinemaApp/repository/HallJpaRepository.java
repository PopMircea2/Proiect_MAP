package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallJpaRepository extends JpaRepository<Hall, String> {
}

