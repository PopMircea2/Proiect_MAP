package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Ticket;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TicketRepo extends MemoryRepo<Ticket>{
    protected TicketRepo() {
        super(Ticket::getId);
    }
}
