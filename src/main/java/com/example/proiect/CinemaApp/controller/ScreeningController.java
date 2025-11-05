package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.service.ScreeningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/screenings")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public String showScreenings(Model model) {
        model.addAttribute("screenings", screeningService.getAllScreenings());
        return "screening/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("screening", new Screening());
        return "screening/form";
    }

    @PostMapping
    public String addScreening(@ModelAttribute Screening screening) {
        screeningService.addScreening(screening);
        return "redirect:/screenings";
    }

    @PostMapping("/{id}/delete")
    public String deleteScreening(@PathVariable String id) {
        screeningService.deleteScreeningbyId(id);
        return "redirect:/screenings";
    }
}
