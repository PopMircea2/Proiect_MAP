package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Ticket;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Ticket_Repo {
    private final Map<String, Ticket> tickets = new HashMap<>();

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    public Optional<Ticket> findById(String id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public Ticket save(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public void deleteById(String id) {
        tickets.remove(id);
    }
}
