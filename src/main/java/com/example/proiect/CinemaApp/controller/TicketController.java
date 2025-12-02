package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Ticket;
import com.example.proiect.CinemaApp.service.TicketService;
import com.example.proiect.CinemaApp.service.ScreeningService;
import com.example.proiect.CinemaApp.service.SeatService;
import com.example.proiect.CinemaApp.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ScreeningService screeningService;
    private final SeatService seatService;
    private final CustomerService customerService;

    public TicketController(TicketService ticketService, ScreeningService screeningService, SeatService seatService, CustomerService customerService) {
        this.ticketService = ticketService;
        this.screeningService = screeningService;
        this.seatService = seatService;
        this.customerService = customerService;
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
        model.addAttribute("screenings", screeningService.getAllScreenings());
        model.addAttribute("seats", seatService.getAllSeats());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "ticket/form";
    }

    @PostMapping
    public String addTicket(@Valid @ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("screenings", screeningService.getAllScreenings());
            model.addAttribute("seats", seatService.getAllSeats());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "ticket/form";
        }
        try {
            ticketService.addTicket(ticket);
            return "redirect:/tickets";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add ticket: " + e.getMessage());
            model.addAttribute("screenings", screeningService.getAllScreenings());
            model.addAttribute("seats", seatService.getAllSeats());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "ticket/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicketbyId(id);
        return "redirect:/tickets";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        ticketService.getTicketById(id).ifPresentOrElse(t -> model.addAttribute("ticket", t), () -> model.addAttribute("ticket", new Ticket()));
        model.addAttribute("screenings", screeningService.getAllScreenings());
        model.addAttribute("seats", seatService.getAllSeats());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "ticket/form-update";
    }

    @PostMapping("/{id}")
    public String updateTicket(@PathVariable String id, @Valid @ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            ticket.setId(id);
            model.addAttribute("screenings", screeningService.getAllScreenings());
            model.addAttribute("seats", seatService.getAllSeats());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "ticket/form-update";
        }
        try {
            ticket.setId(id);
            ticketService.updateTicket(ticket);
            return "redirect:/tickets";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update ticket: " + e.getMessage());
            model.addAttribute("screenings", screeningService.getAllScreenings());
            model.addAttribute("seats", seatService.getAllSeats());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "ticket/form-update";
        }
    }
}
