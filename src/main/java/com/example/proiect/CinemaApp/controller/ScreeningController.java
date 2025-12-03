package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.service.ScreeningService;
import com.example.proiect.CinemaApp.service.HallService;
import com.example.proiect.CinemaApp.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/screenings")
public class ScreeningController {

    private final ScreeningService screeningService;
    private final HallService hallService;
    private final MovieService movieService;

    public ScreeningController(ScreeningService screeningService, HallService hallService, MovieService movieService) {
        this.screeningService = screeningService;
        this.hallService = hallService;
        this.movieService = movieService;
    }

    @GetMapping
    public String showScreenings(Model model) {
        model.addAttribute("screenings", screeningService.getAllScreenings());
        return "screening/index";
    }

    @GetMapping("/{id}")
    public String showScreeningDetails(@PathVariable String id, Model model) {
        screeningService.getScreeningById(id).ifPresentOrElse(s -> model.addAttribute("screening", s), () -> model.addAttribute("screening", new com.example.proiect.CinemaApp.model.Screening()));
        return "screening/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("screening", new Screening());
        model.addAttribute("halls", hallService.getAllHalls());
        model.addAttribute("movies", movieService.getAllMovies());
        return "screening/form";
    }

    @PostMapping
    public String addScreening(@Valid @ModelAttribute Screening screening, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Failed to add screening: " + bindingResult.toString());
            model.addAttribute("halls", hallService.getAllHalls());
            model.addAttribute("movies", movieService.getAllMovies());
            return "screening/form";
        }
        try {
            screeningService.addScreening(screening);
            return "redirect:/screenings";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add screening: " + e.getMessage());
            model.addAttribute("halls", hallService.getAllHalls());
            model.addAttribute("movies", movieService.getAllMovies());
            return "screening/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteScreening(@PathVariable String id) {
        screeningService.deleteScreeningbyId(id);
        return "redirect:/screenings";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        screeningService.getScreeningById(id).ifPresentOrElse(s -> model.addAttribute("screening", s), () -> model.addAttribute("screening", new Screening()));
        model.addAttribute("halls", hallService.getAllHalls());
        model.addAttribute("movies", movieService.getAllMovies());
        return "screening/form-update";
    }

    @PostMapping("/{id}")
    public String updateScreening(@PathVariable String id, @Valid @ModelAttribute Screening screening, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            screening.setId(id);
            model.addAttribute("halls", hallService.getAllHalls());
            model.addAttribute("movies", movieService.getAllMovies());
            return "screening/form-update";
        }
        try {
            screening.setId(id);
            screeningService.updateScreening(screening);
            return "redirect:/screenings";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update screening: " + e.getMessage());
            model.addAttribute("halls", hallService.getAllHalls());
            model.addAttribute("movies", movieService.getAllMovies());
            return "screening/form-update";
        }
    }
}
