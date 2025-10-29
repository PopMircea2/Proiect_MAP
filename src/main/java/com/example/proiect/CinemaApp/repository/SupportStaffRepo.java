package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Staff;

public class SupportStaffRepo extends MemoryRepo<Staff> {
    protected SupportStaffRepo() {
        super(Staff::getId);
    }
}
