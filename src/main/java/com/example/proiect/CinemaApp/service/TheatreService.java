package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.repository.TheatreJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class TheatreService {
    private final TheatreJpaRepository theatreRepo;

    public TheatreService(TheatreJpaRepository theatreRepo) {
        this.theatreRepo = theatreRepo;
    }

    @Transactional(readOnly = true)
    public List<Theatre> getAllTheatres() {
        List<Theatre> list = theatreRepo.findAll();
        for (Theatre t : list) {
            if (t.getHalls() != null) t.getHalls().size();
        }
        return list;
    }

    // New: filterable + sortable getAllTheatres
    @Transactional(readOnly = true)
    public List<Theatre> getAllTheatres(String q, String city, String sortBy, String sortDir) {
        Sort sort = Sort.by((sortBy == null || sortBy.isBlank()) ? "id" : sortBy);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Specification<Theatre> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (q != null && !q.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + q.toLowerCase() + "%"));
            }

            if (city != null && city.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return theatreRepo.findAll(spec, sort);
    }

    @Transactional(readOnly = true)
    public Optional<Theatre> getTheatreById(String id) {
        Optional<Theatre> opt = theatreRepo.findById(id);
        opt.ifPresent(t -> {
            if (t.getHalls() != null) t.getHalls().size();
        });
        return opt;
    }

    public Theatre addTheatre(Theatre theatre) {
        if (theatre.getId() == null || theatre.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (theatreRepo.existsById(theatre.getId())) {
            throw new BusinessValidationException("A theatre with ID '" + theatre.getId() + "' already exists");
        }
        return VerifyTheatre(theatre);
    }

    private Theatre VerifyTheatre(Theatre theatre) {
        if (theatre.getName() == null || theatre.getName().isBlank()) {
            throw new BusinessValidationException("Name is required");
        }
        if (theatre.getCity() == null || theatre.getCity().isBlank()) {
            throw new BusinessValidationException("City is required");
        }
        try {
            return theatreRepo.save(theatre);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save theatre: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save theatre: " + ex.getMessage(), ex);
        }
    }

    public Theatre updateTheatre(Theatre theatre) {
        if (theatre.getId() == null || theatre.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (!theatreRepo.existsById(theatre.getId())) {
            throw new BusinessValidationException("Theatre with ID '" + theatre.getId() + "' does not exist");
        }
        return VerifyTheatre(theatre);
    }

    public void deleteTheatrebyId(String id) {
        theatreRepo.deleteById(id);
    }
}
