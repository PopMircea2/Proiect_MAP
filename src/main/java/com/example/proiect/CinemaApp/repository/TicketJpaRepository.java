package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketJpaRepository extends JpaRepository<Ticket, String>, JpaSpecificationExecutor<Ticket> {
}
