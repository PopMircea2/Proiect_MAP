package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Ticket;
import com.example.proiect.CinemaApp.repository.TicketRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Optional<Ticket> getTicketById(String id) {
        return ticketRepo.findById(id);
    }

    public Ticket addTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public void deleteTicket(String id) {
        ticketRepo.deleteById(id);
    }
}
