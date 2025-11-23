package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Show list of movies
    @GetMapping
    public String showMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie/index";
    }

    // Show form to create a new movie
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie/form";
    }

    // Process form submission
    @PostMapping
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }

    // Delete a movie
    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable String id) {
        movieService.deleteMoviebyId(id);
        return "redirect:/movies";
    }

    // Show form to edit existing movie
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        movieService.getMovieById(id).ifPresentOrElse(m -> model.addAttribute("movie", m), () -> model.addAttribute("movie", new Movie()));
        return "movie/form-update";
    }

    // Process update submission
    @PostMapping("/{id}")
    public String updateMovie(@PathVariable String id, @ModelAttribute Movie movie) {
        // ensure id consistency
        movie.setId(id);
        movieService.addMovie(movie); // add replaces existing
        return "redirect:/movies";
    }
}