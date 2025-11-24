package com.example.proiect.CinemaApp.model;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "Screening")
public class Screening {
    @Id
    @Column(name = "Id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HallId", referencedColumnName = "Id")
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    private Movie movie;

    @Transient
    private String hallId;

    @Transient
    private String movieId;

    @Column(name = "Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffAssignment> assignments;

    public Screening() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getHallId() {
        if (hall != null && hall.getId() != null) return hall.getId();
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getMovieId() {
        if (movie != null && movie.getId() != null) return movie.getId();
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public Screening(String id, Hall hall, Movie movie, LocalDateTime date, List<Ticket> tickets, List<StaffAssignment> assignments) {
        this.id = id;
        this.hall = hall;
        this.movie = movie;
        this.date = date;
        this.tickets = tickets;
        this.assignments = assignments;
    }
}
