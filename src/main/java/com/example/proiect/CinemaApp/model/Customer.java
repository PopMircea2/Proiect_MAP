package com.example.proiect.CinemaApp.model;

import java.util.List;

public class Customer {
    private String id;
    private int age;
    private String Name;
    private List<Ticket> tickets;


    public Customer(String id, int age, String name, List<Ticket> tickets) {
        this.id = id;
        this.age = age;
        Name = name;
        this.tickets = tickets;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
