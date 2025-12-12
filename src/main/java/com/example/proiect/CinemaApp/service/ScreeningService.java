package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Screening;
import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.model.Movie;
import com.example.proiect.CinemaApp.repository.ScreeningJpaRepository;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import com.example.proiect.CinemaApp.repository.MovieJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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

    // New: filterable + sortable getAllScreenings
    @Transactional(readOnly = true)
    public List<Screening> getAllScreenings(String q, String movieId, String dateIso, String sortBy, String sortDir) {
        Sort sort = Sort.by((sortBy == null || sortBy.isBlank()) ? "id" : sortBy);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Specification<Screening> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (q != null && !q.isEmpty()) {
                // search by related movie title
                predicates.add(cb.like(cb.lower(root.join("movie").get("title")), "%" + q.toLowerCase() + "%"));
            }

            if (movieId != null && !movieId.isBlank()) {
                predicates.add(cb.equal(root.get("movie").get("id"), movieId));
            }

            if (dateIso != null && !dateIso.isBlank()) {
                try {
                    LocalDateTime dt = LocalDateTime.parse(dateIso);
                    predicates.add(cb.equal(root.get("date"), dt));
                } catch (DateTimeParseException ex) {
                    // ignore invalid date format filter
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<Screening> list = screeningRepo.findAll(spec, sort);
        // initialize relations used by views
        for (Screening s : list) {
            if (s.getHall() != null) s.getHall().getName();
            if (s.getMovie() != null) s.getMovie().getTitle();
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
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (screeningRepo.existsById(screening.getId())) {
            throw new BusinessValidationException("A screening with ID '" + screening.getId() + "' already exists");
        }

        return VerifyScreening(screening);
    }

    private Screening VerifyScreening(Screening screening) {
        // 1. Strict Validation for Hall ID
        String hId = screening.getHallId();
        if (hId != null && !hId.isBlank()) {
            Hall h = hallRepo.findById(hId)
                    .orElseThrow(() -> new BusinessValidationException("Hall with ID '" + hId + "' does not exist"));
            screening.setHall(h);
        }

        // 2. Strict Validation for Movie ID
        String mId = screening.getMovieId();
        if (mId != null && !mId.isBlank()) {
            Movie m = movieRepo.findById(mId)
                    .orElseThrow(() -> new BusinessValidationException("Movie with ID '" + mId + "' does not exist"));
            screening.setMovie(m);
        }

        if (screening.getHall() == null) throw new BusinessValidationException("Hall is required");
        if (screening.getMovie() == null) throw new BusinessValidationException("Movie is required");

        if (screening.getDate() == null) throw new BusinessValidationException("Date is required");

        try {
            return screeningRepo.save(screening);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save screening: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save screening: " + ex.getMessage(), ex);
        }
    }

    public Screening updateScreening(Screening screening) {
        // validate ID
        if (screening.getId() == null || screening.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (!screeningRepo.existsById(screening.getId())) {
            throw new BusinessValidationException("Screening with ID '" + screening.getId() + "' does not exist");
        }

        return VerifyScreening(screening);
    }

    public void deleteScreeningbyId(String id) {
        screeningRepo.deleteById(id);
    }
}