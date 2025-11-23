package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.service.TheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("theatre", new Theatre());
        return "theatre/form";
    }

    @PostMapping
    public String addTheatre(@ModelAttribute Theatre theatre) {
        theatreService.addTheatre(theatre);
        return "redirect:/theatres";
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
    public String updateTheatre(@PathVariable String id, @ModelAttribute Theatre theatre) {
        theatre.setId(id);
        theatreService.addTheatre(theatre);
        return "redirect:/theatres";
    }
}
