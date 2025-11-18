package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.SupportStaff;
import org.springframework.stereotype.Repository;

@Repository
public class SupportStaffRepo extends InFileRepository<SupportStaff> {
    protected SupportStaffRepo() {
        super(SupportStaff::getId, SupportStaff.class, "supportstaff.json");
    }
}
