package com.example.proiect.CinemaApp.model;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Screening {
    private String id;
    private String hallId;
    private String movieId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private List<Ticket> tickets;
    private List<StaffAssignment> assignments;

    public Screening() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<StaffAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<StaffAssignment> assignments) {
        this.assignments = assignments;
    }

    public Screening(String id, String hallId, String movieId, LocalDate date, List<Ticket> tickets, List<StaffAssignment> assignments) {
        this.id = id;
        this.hallId = hallId;
        this.movieId = movieId;
        this.date = date;
        this.tickets = tickets;
        this.assignments = assignments;
    }
}
