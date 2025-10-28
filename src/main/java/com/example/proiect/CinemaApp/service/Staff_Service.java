package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Staff;
import com.example.proiect.CinemaApp.repository.Staff_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Staff_Service {
    private final Staff_Repo staffRepo;

    public Staff_Service(Staff_Repo staffRepo) {
        this.staffRepo = staffRepo;
    }

    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    public Optional<Staff> getStaffById(String id) {
        return staffRepo.findById(id);
    }

    public Staff addStaff(Staff staff) {
        return staffRepo.save(staff);
    }

    public void deleteStaff(String id) {
        staffRepo.deleteById(id);
    }
}
