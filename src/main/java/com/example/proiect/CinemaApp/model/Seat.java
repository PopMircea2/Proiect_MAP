package com.example.proiect.CinemaApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Seat")
public class Seat {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "HallId")
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

    public String getHallId() {
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

    public Seat(String id, String hallId, String rowLabel, int columnNumber) {
        this.id = id;
        this.hallId = hallId;
        this.rowLabel = rowLabel;
        this.columnNumber = columnNumber;
    }
}
