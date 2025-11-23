package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.service.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addHall(@ModelAttribute Hall hall) {
        hallService.addHall(hall);
        return "redirect:/halls";
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
    public String updateHall(@PathVariable String id, @ModelAttribute Hall hall) {
        hall.setId(id);
        hallService.addHall(hall);
        return "redirect:/halls";
    }
}
