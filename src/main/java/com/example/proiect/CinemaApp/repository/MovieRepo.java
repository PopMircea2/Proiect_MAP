package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Movie;
import java.util.*;

public class MovieRepo extends InFileRepository<Movie>{
    protected MovieRepo() {
        super(Movie::getId, Movie.class, "movies.json");
    }
}
