package com.example.proiect.CinemaApp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "BirthDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateBirth;

    @Transient
    private List<Ticket> tickets = new ArrayList<>();

    public Customer() {
    }

    public Customer(String id, LocalDate dateBirth, String name, List<Ticket> tickets) {
        this.id = id;
        this.dateBirth = dateBirth;
        this.name = name;
        if (tickets != null) this.tickets = tickets;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
