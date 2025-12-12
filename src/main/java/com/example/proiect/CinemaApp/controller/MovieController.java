package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Show list of movies
//    @GetMapping
//    public String showMovies(Model model) {
//        model.addAttribute("movies", movieService.getAllMovies());
//        return "movie/index";
//    }

    // Show details for a specific movie
    @GetMapping("/{id}")
    public String showMovieDetails(@PathVariable String id, Model model) {
        movieService.getMovieById(id).ifPresentOrElse(m -> model.addAttribute("movie", m), () -> model.addAttribute("movie", new com.example.proiect.CinemaApp.model.Movie()));
        return "movie/show";
    }

    // Show form to create a new movie
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie/form";
    }

    // Process form submission
    @PostMapping
    public String addMovie(@Valid @ModelAttribute Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "movie/form";
        }
        try {
            movieService.addMovie(movie);
            return "redirect:/movies";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add movie: " + e.getMessage());
            return "movie/form";
        }
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
    public String updateMovie(@PathVariable String id, @Valid @ModelAttribute Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            movie.setId(id);
            return "movie/form-update";
        }
        try {
            // ensure id consistency
            movie.setId(id);
            movieService.updateMovie(movie);
            return "redirect:/movies";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update movie: " + e.getMessage());
            return "movie/form-update";
        }
    }

    @GetMapping
    public String showMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minRating,
            @RequestParam(defaultValue = "title") String sort, // Default sort by title
            @RequestParam(defaultValue = "asc") String dir,    // Default direction asc
            Model model) {

        // Fetch filtered and sorted list
        List<Movie> movies = movieService.getAllMovies(title, minRating, sort, dir);

        // Add data to model
        model.addAttribute("movies", movies);

        // Add current filter/sort values to model (to preserve them in the UI)
        model.addAttribute("paramTitle", title);
        model.addAttribute("paramMinRating", minRating);
        model.addAttribute("paramSort", sort);
        model.addAttribute("paramDir", dir);

        // Calculate reverse direction for the table headers (if currently asc, next click should be desc)
        model.addAttribute("reverseDir", "asc".equals(dir) ? "desc" : "asc");

        return "movie/index";
    }
}