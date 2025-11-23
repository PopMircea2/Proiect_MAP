package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, String> {
}

