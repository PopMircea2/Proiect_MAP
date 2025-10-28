package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Movie;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Movie_Repo {
    private final Map<String, Movie> movies = new HashMap<>();

    public List<Movie> findAll() {
        return new ArrayList<>(movies.values());
    }

    public Optional<Movie> findById(String id) {
        return Optional.ofNullable(movies.get(id));
    }

    public Movie save(Movie movie) {
        movies.put(movie.getId(), movie);
        return movie;
    }

    public void deleteById(String id) {
        movies.remove(id);
    }
}

