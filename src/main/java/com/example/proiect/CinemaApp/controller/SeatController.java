package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public String showSeats(Model model) {
        model.addAttribute("seats", seatService.getAllSeats());
        return "seat/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("seat", new Seat());
        return "seat/form";
    }

    @PostMapping
    public String addSeat(@ModelAttribute Seat seat) {
        seatService.addSeat(seat);
        return "redirect:/seats";
    }

    @PostMapping("/{id}/delete")
    public String deleteSeat(@PathVariable String id) {
        seatService.deleteSeatbyId(id);
        return "redirect:/seats";
    }
}
