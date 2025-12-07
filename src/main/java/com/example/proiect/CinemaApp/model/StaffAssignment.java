package com.example.proiect.CinemaApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "StaffAssignment")
public class StaffAssignment {
    @Id
    @NotBlank(message = "ID is required")
    @Column(name = "Id")
    private String id;

    //@NotNull(message = "Screening is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ScreeningId", referencedColumnName = "Id")
    private Screening screening;

    //@NotNull(message = "Staff is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffId", referencedColumnName = "Id")
    private Staff staff;

    @Transient
    private String screeningId;

    @Transient
    private String staffId;

    public StaffAssignment() {}

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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getScreeningId() {
        if (screening != null && screening.getId() != null) return screening.getId();
        return screeningId;
    }

    public void setScreeningId(String screeningId) {
        this.screeningId = screeningId;
    }

    public String getStaffId() {
        if (staff != null && staff.getId() != null) return staff.getId();
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public StaffAssignment(String id, Screening screening, Staff staff) {
        this.id = id;
        this.screening = screening;
        this.staff = staff;
    }
}
