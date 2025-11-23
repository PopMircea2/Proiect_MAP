package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Ticket;
import com.example.proiect.CinemaApp.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String showTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "ticket/index";
    }

    @GetMapping("/{id}")
    public String showTicketDetails(@PathVariable String id, Model model) {
        ticketService.getTicketById(id).ifPresentOrElse(t -> model.addAttribute("ticket", t), () -> model.addAttribute("ticket", new com.example.proiect.CinemaApp.model.Ticket()));
        return "ticket/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket/form";
    }

    @PostMapping
    public String addTicket(@ModelAttribute Ticket ticket) {
        ticketService.addTicket(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicketbyId(id);
        return "redirect:/tickets";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        ticketService.getTicketById(id).ifPresentOrElse(t -> model.addAttribute("ticket", t), () -> model.addAttribute("ticket", new Ticket()));
        return "ticket/form-update";
    }

    @PostMapping("/{id}")
    public String updateTicket(@PathVariable String id, @ModelAttribute Ticket ticket) {
        ticket.setId(id);
        ticketService.addTicket(ticket);
        return "redirect:/tickets";
    }
}
