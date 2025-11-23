package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.SupportStaff;
import com.example.proiect.CinemaApp.repository.SupportStaffJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportStaffService {
    private final SupportStaffJpaRepository supportStaffRepo;

    public SupportStaffService(SupportStaffJpaRepository supportStaffRepo) {
        this.supportStaffRepo = supportStaffRepo;
    }

    public List<SupportStaff> getAllSupportStaff() {
        return supportStaffRepo.findAll();
    }

    public Optional<SupportStaff> getSupportStaffById(String id) {
        return supportStaffRepo.findById(id);
    }

    public SupportStaff addSupportStaff(SupportStaff staff) {
        return supportStaffRepo.save(staff);
    }

    public void deleteSupportStaffbyId(String id) {
        supportStaffRepo.deleteById(id);
    }
}
