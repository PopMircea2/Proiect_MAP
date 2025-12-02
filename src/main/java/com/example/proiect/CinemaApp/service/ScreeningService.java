package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import com.example.proiect.CinemaApp.repository.MovieJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScreeningService {
    private final ScreeningJpaRepository screeningRepo;
    private final HallJpaRepository hallRepo;
    private final MovieJpaRepository movieRepo;

    public ScreeningService(ScreeningJpaRepository screeningRepo, HallJpaRepository hallRepo, MovieJpaRepository movieRepo) {
        this.screeningRepo = screeningRepo;
        this.hallRepo = hallRepo;
        this.movieRepo = movieRepo;
    }

    @Transactional(readOnly = true)
    public List<Screening> getAllScreenings() {
        List<Screening> list = screeningRepo.findAll();
        // initialize relations used by views
        for (Screening s : list) {
            if (s.getHall() != null) {
                s.getHall().getName();
            }
            if (s.getMovie() != null) {
                s.getMovie().getTitle();
            }
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Optional<Screening> getScreeningById(String id) {
        Optional<Screening> opt = screeningRepo.findById(id);
        opt.ifPresent(s -> {
            if (s.getHall() != null) s.getHall().getName();
            if (s.getMovie() != null) s.getMovie().getTitle();
            if (s.getTickets() != null) s.getTickets().size();
            if (s.getAssignments() != null) s.getAssignments().size();
        });
        return opt;
    }

    public Screening addScreening(Screening screening) {
        // validate ID
        if (screening.getId() == null || screening.getId().isBlank()) {
            throw new IllegalArgumentException("ID is required and cannot be empty");
        }
        if (screeningRepo.existsById(screening.getId())) {
            throw new IllegalArgumentException("A screening with ID '" + screening.getId() + "' already exists");
        }

        // resolve hall/movie
        if (screening.getHallId() != null && !screening.getHallId().isBlank()) {
            Hall h = hallRepo.findById(screening.getHallId()).orElse(null);
            screening.setHall(h);
        }
        if (screening.getMovieId() != null && !screening.getMovieId().isBlank()) {
            Movie m = movieRepo.findById(screening.getMovieId()).orElse(null);
            screening.setMovie(m);
        }

        // validate required fields
        if (screening.getHall() == null) throw new IllegalArgumentException("Hall is required");
        if (screening.getMovie() == null) throw new IllegalArgumentException("Movie is required");
        if (screening.getDate() == null) throw new IllegalArgumentException("Date is required");
        try {
            return screeningRepo.save(screening);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Failed to save screening: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save screening: " + ex.getMessage(), ex);
        }
    }

    public void deleteScreeningbyId(String id) {
        screeningRepo.deleteById(id);
    }
}
