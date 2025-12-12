package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; // Import this

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, String>, JpaSpecificationExecutor<Movie> {
    // JpaSpecificationExecutor adds methods like findAll(Specification<T> spec, Sort sort)
}

