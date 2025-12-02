package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.MovieJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {
    private final MovieJpaRepository movieRepo;

    public MovieService(MovieJpaRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepo.findById(id);
    }

    public Movie addMovie(Movie movie) {
        // Check if ID is null or blank
        if (movie.getId() == null || movie.getId().isBlank()) {
            throw new IllegalArgumentException("ID is required and cannot be empty");
        }
        // Check if ID already exists
        if (movieRepo.existsById(movie.getId())) {
            throw new IllegalArgumentException("A movie with ID '" + movie.getId() + "' already exists");
        }
        // validate mandatory fields
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (movie.getDurationMin() <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        try {
            return movieRepo.save(movie);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Failed to save movie: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save movie: " + ex.getMessage(), ex);
        }
    }

    public void deleteMoviebyId(String id) {
        movieRepo.deleteById(id);
    }
}
