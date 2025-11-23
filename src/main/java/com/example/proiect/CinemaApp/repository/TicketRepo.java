package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Ticket;
import java.util.*;

public class TicketRepo extends InFileRepository<Ticket>{
    protected TicketRepo() {
        super(t -> t.getId() == null ? null : t.getId().toString(), Ticket.class, "tickets.json");
    }
}
