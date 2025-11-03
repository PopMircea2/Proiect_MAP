package com.example.proiect.CinemaApp.model;

public class Seat {
    private String id;
    private String hallId;
    private String row;
    private String column;

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

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Seat(String id, String hallId, String row, String column) {
        this.id = id;
        this.hallId = hallId;
        this.row = row;
        this.column = column;
    }
}
