package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.MovieJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return verifyMovie(movie);
    }

    private Movie verifyMovie(Movie movie) {
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
        return verifyMovie(movie);
    }

    public void deleteMoviebyId(String id) {
        movieRepo.deleteById(id);
    }


    public List<Movie> getAllMovies(String title, Double minRating, String sortBy, String sortDir) {
        // 1. Build the Sort object
        Sort sort = Sort.by(sortBy);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        // 2. Build the Specification (Dynamic Filtering)
        Specification<Movie> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by Title (if provided)
            if (title != null && !title.isEmpty()) {
                // roughly: WHERE lower(title) LIKE %lower(input)%
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            // Filter by Minimum Rating (if provided)
            if (minRating != null) {
                // roughly: WHERE rating >= minRating
                predicates.add(cb.greaterThanOrEqualTo(root.get("Rating"), minRating));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        // 3. Execute Query with Filter AND Sort
        return movieRepo.findAll(spec, sort);
    }
}
