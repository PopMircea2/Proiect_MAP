package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import com.example.proiect.CinemaApp.repository.TechnicalOperatorJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalOperatorService {
    private final TechnicalOperatorJpaRepository technicalOperatorRepo;

    public TechnicalOperatorService(TechnicalOperatorJpaRepository technicalOperatorRepo) {
        this.technicalOperatorRepo = technicalOperatorRepo;
    }

    public List<TechnicalOperator> getAllTechnicalOperators() {
        return technicalOperatorRepo.findAll();
    }

    public Optional<TechnicalOperator> getTechnicalOperatorById(String id) {
        return technicalOperatorRepo.findById(id);
    }

    public TechnicalOperator addTechnicalOperator(TechnicalOperator technicalOperator) {
        return technicalOperatorRepo.save(technicalOperator);
    }

    public void deleteTechnicalOperatorbyId(String id) {
        technicalOperatorRepo.deleteById(id);
    }
}
