package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.MovieJpaRepository;
import org.springframework.stereotype.Service;
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
        return movieRepo.save(movie);
    }

    public void deleteMoviebyId(String id) {
        movieRepo.deleteById(id);
    }
}
