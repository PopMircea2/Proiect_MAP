package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    private final ScreeningJpaRepository screeningRepo;

    public ScreeningService(ScreeningJpaRepository screeningRepo) {
        this.screeningRepo = screeningRepo;
    }

    public List<Screening> getAllScreenings() {
        return screeningRepo.findAll();
    }

    public Optional<Screening> getScreeningById(String id) {
        return screeningRepo.findById(id);
    }

    public Screening addScreening(Screening screening) {
        return screeningRepo.save(screening);
    }

    public void deleteScreeningbyId(String id) {
        screeningRepo.deleteById(id);
    }
}
