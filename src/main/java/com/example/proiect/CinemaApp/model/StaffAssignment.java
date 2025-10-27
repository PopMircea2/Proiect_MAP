package com.example.proiect.CinemaApp.model;

public class StaffAssignment {
    private String id;
    private String ScreeningId;
    private String StaffId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScreeningId() {
        return ScreeningId;
    }

    public void setScreeningId(String screeningId) {
        ScreeningId = screeningId;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public StaffAssignment(String id, String screeningId, String staffId) {
        this.id = id;
        ScreeningId = screeningId;
        StaffId = staffId;
    }
}
