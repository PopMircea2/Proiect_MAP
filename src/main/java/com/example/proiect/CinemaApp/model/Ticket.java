package com.example.proiect.CinemaApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @NotBlank(message = "ID is required")
    @Column(name = "Id")
    private String id;

    //@NotNull(message = "Screening is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ScreeningId", referencedColumnName = "Id")
    private Screening screening;

    //@NotNull(message = "Seat is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SeatId", referencedColumnName = "Id")
    private Seat seat;

    @Positive(message = "Price must be positive")
    @Column(name = "Price")
    private double price;

    //@NotNull(message = "Customer is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", referencedColumnName = "Id")
    private Customer customer;

    @Transient
    private String screeningId;

    @Transient
    private String seatId;

    @Transient
    private String customerId;

    public Ticket() {}

    public Ticket(String id, Screening screening, Customer customer, Seat seat, double price) {
        this.id = id;
        this.screening = screening;
        this.customer = customer;
        this.seat = seat;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getScreeningId() {
        if (screening != null && screening.getId() != null) return screening.getId();
        return screeningId;
    }

    public void setScreeningId(String screeningId) {
        this.screeningId = screeningId;
    }

    public String getSeatId() {
        if (seat != null && seat.getId() != null) return seat.getId();
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getCustomerId() {
        if (customer != null && customer.getId() != null) return customer.getId();
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
