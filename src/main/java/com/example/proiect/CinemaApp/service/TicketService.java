package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Ticket;
import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.TicketJpaRepository;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import com.example.proiect.CinemaApp.repository.SeatJpaRepository;
import com.example.proiect.CinemaApp.repository.CustomerJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (ticket.getId() == null || ticket.getId().isBlank()){
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (ticketRepo.existsById(ticket.getId())) {
            throw new BusinessValidationException("Ticket with ID '" + ticket.getId() + "' already exists");
        }

        return VerifyTicket(ticket);
    }

    private Ticket VerifyTicket(Ticket ticket) {
        if (ticket.getScreeningId() != null && !ticket.getScreeningId().isBlank()) {
            Screening s = screeningRepo.findById(ticket.getScreeningId())
                    .orElseThrow(() -> new BusinessValidationException("Screening with ID '" + ticket.getScreeningId() + "' does not exist"));
            ticket.setScreening(s);
        }

        if (ticket.getSeatId() != null && !ticket.getSeatId().isBlank()) {
            Seat st = seatRepo.findById(ticket.getSeatId())
                    .orElseThrow(() -> new BusinessValidationException("Seat with ID '" + ticket.getSeatId() + "' does not exist"));
            ticket.setSeat(st);
        }

        if (ticket.getCustomerId() != null && !ticket.getCustomerId().isBlank()) {
            Customer c = customerRepo.findById(ticket.getCustomerId())
                    .orElseThrow(() -> new BusinessValidationException("Customer with ID '" + ticket.getCustomerId() + "' does not exist"));
            ticket.setCustomer(c);
        }

        if (ticket.getScreening() == null) throw new BusinessValidationException("Screening is required");
        if (ticket.getSeat() == null) throw new BusinessValidationException("Seat is required");
        if (ticket.getCustomer() == null) throw new BusinessValidationException("Customer is required");
        if (ticket.getPrice() <= 0.0) throw new BusinessValidationException("Price must be positive");

        try {
            return ticketRepo.save(ticket);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save ticket: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save ticket: " + ex.getMessage(), ex);
        }
    }

    public Ticket updateTicket(Ticket ticket) {
        if (ticket.getId() == null || ticket.getId().isBlank()) {
            throw new BusinessValidationException("ID is required for update");
        }
        if (!ticketRepo.existsById(ticket.getId())) {
            throw new BusinessValidationException("Ticket with ID '" + ticket.getId() + "' does not exist");
        }

        return VerifyTicket(ticket);
    }

    public void deleteTicketbyId(String id) {
        ticketRepo.deleteById(id);
    }
}
