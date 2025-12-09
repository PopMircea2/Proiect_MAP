package com.example.proiect.CinemaApp.service;

import com.example.proiect.CinemaApp.model.Seat;
import com.example.proiect.CinemaApp.model.Hall;
import com.example.proiect.CinemaApp.repository.SeatJpaRepository;
import com.example.proiect.CinemaApp.repository.HallJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    private final SeatJpaRepository seatRepo;
    private final HallJpaRepository hallRepo;

    public SeatService(SeatJpaRepository seatRepo, HallJpaRepository hallRepo) {
        this.seatRepo = seatRepo;
        this.hallRepo = hallRepo;
    }

    public List<Seat> getAllSeats() {
        return seatRepo.findAll();
    }

    public Optional<Seat> getSeatById(String id) {
        return seatRepo.findById(id);
    }

    public Seat addSeat(Seat seat) {
        if (seat.getId() == null || seat.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (seatRepo.existsById(seat.getId())) {
            throw new BusinessValidationException("Seat with ID '" + seat.getId() + "' exists already");
        }
        return Verifyseat(seat);
    }
    private Seat Verifyseat(Seat seat) {
        String hallId = seat.getHallId();
        if (hallId != null && !hallId.isBlank()) {
            Hall h = hallRepo.findById(hallId)
                    .orElseThrow(() -> new BusinessValidationException("Hall with ID '" + hallId + "' does not exist"));
            seat.setHall(h);
        }
        if (seat.getHall() == null) throw new BusinessValidationException("Hall is required");
        if (seat.getRowLabel() == null || seat.getRowLabel().isBlank()) throw new BusinessValidationException("Row label is required");
        if (seat.getColumnNumber() <= 0) throw new BusinessValidationException("Column number must be positive");

        try {
            return seatRepo.save(seat);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessValidationException("Failed to save seat: " + ex.getMostSpecificCause().getMessage(), ex);
        } catch (Exception ex) {
            throw new BusinessValidationException("Failed to save seat: " + ex.getMessage(), ex);
        }
    }

    public Seat updateSeat(Seat seat) {
        if (seat.getId() == null || seat.getId().isBlank()) {
            throw new BusinessValidationException("ID is required for update");
        }
        if (!seatRepo.existsById(seat.getId())) {
            throw new BusinessValidationException("Seat with ID '" + seat.getId() + "' does not exist");
        }
        return Verifyseat(seat);
    }

    public void deleteSeatbyId(String id) {
        seatRepo.deleteById(id);
    }
}
