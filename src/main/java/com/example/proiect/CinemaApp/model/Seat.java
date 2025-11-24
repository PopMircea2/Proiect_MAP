package com.example.proiect.CinemaApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Seat")
public class Seat {
    @Id
    @Column(name = "Id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HallId", referencedColumnName = "Id")
    private Hall hall;

    @Transient
    private String hallId;

    @Column(name = "RowLabel")
    private String rowLabel;

    @Column(name = "ColumnNumber")
    private int columnNumber;

    public Seat() {}

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

    public String getHallId() {
        // prefer the actual relationship id when available
        if (hall != null && hall.getId() != null) return hall.getId();
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Seat(String id, Hall hall, String rowLabel, int columnNumber) {
        this.id = id;
        this.hall = hall;
        this.rowLabel = rowLabel;
        this.columnNumber = columnNumber;
    }
}
