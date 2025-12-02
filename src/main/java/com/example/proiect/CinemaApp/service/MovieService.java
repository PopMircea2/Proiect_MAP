package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.MovieJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
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
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        // Check if ID already exists
        if (movieRepo.existsById(movie.getId())) {
            throw new BusinessValidationException("A movie with ID '" + movie.getId() + "' already exists");
        }
        // validate mandatory fields
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new BusinessValidationException("Title is required");
        }
        if (movie.getDurationMin() <= 0) {
            throw new BusinessValidationException("Duration must be positive");
        }
        try {
            return movieRepo.save(movie);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save movie: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save movie: " + ex.getMessage(), ex);
        }
    }

    public Movie updateMovie(Movie movie) {
        // Check if ID is null or blank
        if (movie.getId() == null || movie.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        // Check if movie exists
        if (!movieRepo.existsById(movie.getId())) {
            throw new BusinessValidationException("Movie with ID '" + movie.getId() + "' does not exist");
        }
        // validate mandatory fields
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new BusinessValidationException("Title is required");
        }
        if (movie.getDurationMin() <= 0) {
            throw new BusinessValidationException("Duration must be positive");
        }
        try {
            return movieRepo.save(movie);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save movie: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save movie: " + ex.getMessage(), ex);
        }
    }

    public void deleteMoviebyId(String id) {
        movieRepo.deleteById(id);
    }
}
