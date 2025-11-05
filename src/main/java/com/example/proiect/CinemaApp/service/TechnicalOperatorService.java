package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Staff;
import com.example.proiect.CinemaApp.repository.TechnicalOperatorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalOperatorService {
    private final TechnicalOperatorRepo technicalOperatorRepo;

    public TechnicalOperatorService(TechnicalOperatorRepo technicalOperatorRepo) {
        this.technicalOperatorRepo = technicalOperatorRepo;
    }

    public List<Staff> getAllTechnicalOperators() {
        return technicalOperatorRepo.findAll();
    }

    public Optional<Staff> getTechnicalOperatorById(String id) {
        return technicalOperatorRepo.findById(id);
    }

    public Staff addTechnicalOperator(Staff staff) {
        return technicalOperatorRepo.add(staff);
    }

    public void deleteTechnicalOperatorbyId(String id) {
        technicalOperatorRepo.deleteById(id);
    }
}
