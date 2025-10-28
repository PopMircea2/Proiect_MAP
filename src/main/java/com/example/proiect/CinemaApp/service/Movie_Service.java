package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.Movie_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Movie_Service {
    private final Movie_Repo movieRepo;

    public Movie_Service(Movie_Repo movieRepo) {
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

    public void deleteMovie(String id) {
        movieRepo.deleteById(id);
    }
}
