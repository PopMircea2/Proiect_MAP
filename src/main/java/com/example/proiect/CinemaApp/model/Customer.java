package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;
import java.util.List;

public class Customer {
    private String id;
    private LocalDate DateBirth;
    private String Name;
    private List<Ticket> tickets;


    public Customer() {
    }

    public Customer(String id, LocalDate DateBirth, String name, List<Ticket> tickets) {
        this.id = id;
        this.DateBirth = DateBirth;
        Name = name;
        this.tickets = tickets;
    }

    public LocalDate getDateBirth() {
        return DateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.DateBirth = dateBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
