package com.example.proiect.CinemaApp.model;

import java.util.List;

public class Customer {
    private String id;
    private String Name;
    private List<Ticket> tickets;

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

    public Customer(String id, String name, List<Ticket> tickets) {
        this.id = id;
        Name = name;
        this.tickets = tickets;
    }
}
