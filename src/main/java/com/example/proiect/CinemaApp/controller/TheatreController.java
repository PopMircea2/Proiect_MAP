package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.service.TheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public String showTheatres(Model model) {
        model.addAttribute("theatres", theatreService.getAllTheatres());
        return "theatre/index";
    }

    @GetMapping("/{id}")
    public String showTheatreDetails(@PathVariable String id, Model model) {
        theatreService.getTheatreById(id).ifPresentOrElse(t -> model.addAttribute("theatre", t), () -> model.addAttribute("theatre", new com.example.proiect.CinemaApp.model.Theatre()));
        return "theatre/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("theatre", new Theatre());
        return "theatre/form";
    }

    @PostMapping
    public String addTheatre(@Valid @ModelAttribute Theatre theatre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "theatre/form";
        }
        try {
            theatreService.addTheatre(theatre);
            return "redirect:/theatres";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add theatre: " + e.getMessage());
            return "theatre/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteTheatre(@PathVariable String id) {
        theatreService.deleteTheatrebyId(id);
        return "redirect:/theatres";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        theatreService.getTheatreById(id).ifPresentOrElse(t -> model.addAttribute("theatre", t), () -> model.addAttribute("theatre", new Theatre()));
        return "theatre/form-update";
    }

    @PostMapping("/{id}")
    public String updateTheatre(@PathVariable String id, @Valid @ModelAttribute Theatre theatre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            theatre.setId(id);
            return "theatre/form-update";
        }
        try {
            theatre.setId(id);
            theatreService.updateTheatre(theatre);
            return "redirect:/theatres";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update theatre: " + e.getMessage());
            return "theatre/form-update";
        }
    }
}
