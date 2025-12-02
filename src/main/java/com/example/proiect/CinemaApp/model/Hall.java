package com.example.proiect.CinemaApp.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Hall")
public class Hall {
    @Id
    @NotBlank(message = "ID is required")
    @Column(name = "Id")
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    @Column(name = "Name")
    private String name;

    @NotNull(message = "Theatre is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TheatreId", referencedColumnName = "Id")
    private Theatre theatre;

    @Positive(message = "Capacity must be positive")
    @Column(name = "Capacity")
    private int capacity;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings;

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
    public Theatre getTheatre() {
        return theatre;
    }
    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    public List<Screening> getScreenings() {
        return screenings;
    }
    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }
    public Hall() {}

    public Hall(String id, String name, Theatre theatre, int capacity, List<Seat> seats, List<Screening> screenings) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.capacity = capacity;
        this.seats = seats;
        this.screenings = screenings;
    }
}
