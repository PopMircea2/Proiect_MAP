package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Movie;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MovieRepo extends InFileRepository<Movie>{
    public MovieRepo() {
        super(Movie::getId, Movie.class, "movies.json");
    }

}
