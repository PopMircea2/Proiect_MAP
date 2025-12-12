package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.service.HallService;
import com.example.proiect.CinemaApp.service.TheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/halls")
public class HallController {

    private final HallService hallService;
    private final TheatreService theatreService;

    public HallController(HallService hallService, TheatreService theatreService) {
        this.hallService = hallService;
        this.theatreService = theatreService;
    }

    @GetMapping
    public String showHalls(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String theatreId,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            Model model) {
        model.addAttribute("halls", hallService.getAllHalls(q, theatreId, sort, dir));
        model.addAttribute("paramQ", q);
        model.addAttribute("paramTheatreId", theatreId);
        model.addAttribute("paramSort", sort);
        model.addAttribute("paramDir", dir);
        model.addAttribute("reverseDir", "asc".equals(dir) ? "desc" : "asc");
        return "hall/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("hall", new Hall());
        model.addAttribute("theatres", theatreService.getAllTheatres());
        return "hall/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        hallService.getHallById(id).ifPresentOrElse(h -> model.addAttribute("hall", h), () -> model.addAttribute("hall", new Hall()));
        model.addAttribute("theatres", theatreService.getAllTheatres());
        return "hall/form-update";
    }

    @GetMapping("/{id}")
    public String showHallDetails(@PathVariable String id, Model model) {
        hallService.getHallById(id).ifPresentOrElse(h -> model.addAttribute("hall", h), () -> model.addAttribute("hall", new com.example.proiect.CinemaApp.model.Hall()));
        return "hall/show";
    }

    @PostMapping
    public String addHall(@Valid @ModelAttribute Hall hall, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("theatres", theatreService.getAllTheatres());
            return "hall/form";
        }
        try {
            hallService.addHall(hall);
            return "redirect:/halls";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add hall: " + e.getMessage());
            model.addAttribute("theatres", theatreService.getAllTheatres());
            return "hall/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteHall(@PathVariable String id) {
        hallService.deleteHallbyId(id);
        return "redirect:/halls";
    }

    @PostMapping("/{id}")
    public String updateHall(@PathVariable String id, @Valid @ModelAttribute Hall hall, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            hall.setId(id);
            model.addAttribute("theatres", theatreService.getAllTheatres());
            return "hall/form-update";
        }
        try {
            hall.setId(id);
            hallService.updateHall(hall);
            return "redirect:/halls";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update hall: " + e.getMessage());
            model.addAttribute("theatres", theatreService.getAllTheatres());
            return "hall/form-update";
        }
    }
}
