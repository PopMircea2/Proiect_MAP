package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Ticket;
import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.TicketJpaRepository;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import com.example.proiect.CinemaApp.repository.SeatJpaRepository;
import com.example.proiect.CinemaApp.repository.CustomerJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketJpaRepository ticketRepo;
    private final ScreeningJpaRepository screeningRepo;
    private final SeatJpaRepository seatRepo;
    private final CustomerJpaRepository customerRepo;

    public TicketService(TicketJpaRepository ticketRepo, ScreeningJpaRepository screeningRepo, SeatJpaRepository seatRepo, CustomerJpaRepository customerRepo) {
        this.ticketRepo = ticketRepo;
        this.screeningRepo = screeningRepo;
        this.seatRepo = seatRepo;
        this.customerRepo = customerRepo;
    }

    @Transactional(readOnly = true)
    public List<Ticket> getAllTickets() {
        List<Ticket> list = ticketRepo.findAll();
        for (Ticket t : list) {
            if (t.getScreening() != null) t.getScreening().getId();
            if (t.getCustomer() != null) t.getCustomer().getName();
            if (t.getSeat() != null) t.getSeat().getRowLabel();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Optional<Ticket> getTicketById(String id) {
        Optional<Ticket> opt = ticketRepo.findById(id);
        opt.ifPresent(t -> {
            if (t.getScreening() != null) t.getScreening().getId();
            if (t.getCustomer() != null) t.getCustomer().getName();
            if (t.getSeat() != null) t.getSeat().getRowLabel();
        });
        return opt;
    }

    public Ticket addTicket(Ticket ticket) {
        if (ticket.getScreeningId() != null && !ticket.getScreeningId().isBlank()) {
            Screening s = screeningRepo.findById(ticket.getScreeningId()).orElse(null);
            ticket.setScreening(s);
        }
        if (ticket.getSeatId() != null && !ticket.getSeatId().isBlank()) {
            Seat st = seatRepo.findById(ticket.getSeatId()).orElse(null);
            ticket.setSeat(st);
        }
        if (ticket.getCustomerId() != null && !ticket.getCustomerId().isBlank()) {
            Customer c = customerRepo.findById(ticket.getCustomerId()).orElse(null);
            ticket.setCustomer(c);
        }
        return ticketRepo.save(ticket);
    }

    public void deleteTicketbyId(String id) {
        ticketRepo.deleteById(id);
    }
}
