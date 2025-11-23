package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Ticket;
import com.example.proiect.CinemaApp.repository.TicketJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketJpaRepository ticketRepo;

    public TicketService(TicketJpaRepository ticketRepo) {
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

    public void deleteTicketbyId(String id) {
        ticketRepo.deleteById(id);
    }
}
