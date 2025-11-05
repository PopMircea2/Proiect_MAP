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
}
