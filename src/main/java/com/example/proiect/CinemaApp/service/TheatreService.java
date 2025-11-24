package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Theatre;
import com.example.proiect.CinemaApp.repository.TheatreJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Optional<Theatre> getTheatreById(String id) {
        Optional<Theatre> opt = theatreRepo.findById(id);
        opt.ifPresent(t -> {
            if (t.getHalls() != null) t.getHalls().size();
        });
        return opt;
    }

    public Theatre addTheatre(Theatre theatre) {
        return theatreRepo.save(theatre);
    }

    public void deleteTheatrebyId(String id) {
        theatreRepo.deleteById(id);
    }
}
