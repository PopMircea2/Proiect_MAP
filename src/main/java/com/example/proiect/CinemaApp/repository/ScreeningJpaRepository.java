package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningJpaRepository extends JpaRepository<Screening, String> {
}

