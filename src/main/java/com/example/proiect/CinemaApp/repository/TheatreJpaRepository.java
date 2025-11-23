package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreJpaRepository extends JpaRepository<Theatre, String> {
}

