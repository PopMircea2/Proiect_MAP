package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.service.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public String showHalls(Model model) {
        model.addAttribute("halls", hallService.getAllHalls());
        return "hall/index";
    }

    @GetMapping("/{id}")
    public String showHallDetails(@PathVariable String id, Model model) {
        hallService.getHallById(id).ifPresentOrElse(h -> model.addAttribute("hall", h), () -> model.addAttribute("hall", new com.example.proiect.CinemaApp.model.Hall()));
        return "hall/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("hall", new Hall());
        return "hall/form";
    }

    @PostMapping
    public String addHall(@Valid @ModelAttribute Hall hall, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "hall/form";
        }
        try {
            hallService.addHall(hall);
            return "redirect:/halls";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add hall: " + e.getMessage());
            return "hall/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteHall(@PathVariable String id) {
        hallService.deleteHallbyId(id);
        return "redirect:/halls";
    }

    // Edit endpoints
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        hallService.getHallById(id).ifPresentOrElse(h -> model.addAttribute("hall", h), () -> model.addAttribute("hall", new Hall()));
        return "hall/form-update";
    }

    @PostMapping("/{id}")
    public String updateHall(@PathVariable String id, @Valid @ModelAttribute Hall hall, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            hall.setId(id);
            return "hall/form-update";
        }
        try {
            hall.setId(id);
            hallService.addHall(hall);
            return "redirect:/halls";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update hall: " + e.getMessage());
            return "hall/form-update";
        }
    }
}
