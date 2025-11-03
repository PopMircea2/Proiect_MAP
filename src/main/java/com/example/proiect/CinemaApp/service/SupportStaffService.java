package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Staff;
import com.example.proiect.CinemaApp.repository.SupportStaffRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportStaffService {
    private final SupportStaffRepo supportStaffRepo;

    public SupportStaffService(SupportStaffRepo supportStaffRepo) {
        this.supportStaffRepo = supportStaffRepo;
    }

    public List<Staff> getAllSupportStaff() {
        return supportStaffRepo.findAll();
    }

    public Optional<Staff> getSupportStaffById(String id) {
        return supportStaffRepo.findById(id);
    }

    public Staff addSupportStaff(Staff staff) {
        return supportStaffRepo.save(staff);
    }

    public void deleteSupportStaff(String id) {
        supportStaffRepo.deleteById(id);
    }
}
