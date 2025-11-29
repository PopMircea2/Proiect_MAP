package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.service.SeatService;
import com.example.proiect.CinemaApp.service.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;
    private final HallService hallService;

    public SeatController(SeatService seatService, HallService hallService) {
        this.seatService = seatService;
        this.hallService = hallService;
    }

    @GetMapping
    public String showSeats(Model model) {
        model.addAttribute("seats", seatService.getAllSeats());
        return "seat/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("seat", new Seat());
        model.addAttribute("halls", hallService.getAllHalls());
        return "seat/form";
    }

    @PostMapping
    public String addSeat(@Valid @ModelAttribute Seat seat, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("halls", hallService.getAllHalls());
            return "seat/form";
        }
        try {
            seatService.addSeat(seat);
            return "redirect:/seats";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add seat: " + e.getMessage());
            model.addAttribute("halls", hallService.getAllHalls());
            return "seat/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteSeat(@PathVariable String id) {
        seatService.deleteSeatbyId(id);
        return "redirect:/seats";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        seatService.getSeatById(id).ifPresentOrElse(s -> model.addAttribute("seat", s), () -> model.addAttribute("seat", new Seat()));
        model.addAttribute("halls", hallService.getAllHalls());
        return "seat/form-update";
    }

    @PostMapping("/{id}")
    public String updateSeat(@PathVariable String id, @Valid @ModelAttribute Seat seat, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            seat.setId(id);
            model.addAttribute("halls", hallService.getAllHalls());
            return "seat/form-update";
        }
        try {
            seat.setId(id);
            seatService.addSeat(seat);
            return "redirect:/seats";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update seat: " + e.getMessage());
            model.addAttribute("halls", hallService.getAllHalls());
            return "seat/form-update";
        }
    }
}
