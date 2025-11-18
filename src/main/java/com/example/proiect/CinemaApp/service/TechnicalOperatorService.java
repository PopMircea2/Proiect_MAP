package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
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

    public List<TechnicalOperator> getAllTechnicalOperators() {
        return technicalOperatorRepo.findAll();
    }

    public Optional<TechnicalOperator> getTechnicalOperatorById(String id) {
        return technicalOperatorRepo.findById(id);
    }

    public TechnicalOperator addTechnicalOperator(TechnicalOperator staff) {
        return technicalOperatorRepo.add(staff);
    }

    public void deleteTechnicalOperatorbyId(String id) {
        technicalOperatorRepo.deleteById(id);
    }
}
