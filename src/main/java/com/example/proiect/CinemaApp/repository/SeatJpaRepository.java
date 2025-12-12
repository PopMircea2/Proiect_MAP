package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatJpaRepository extends JpaRepository<Seat, String>, JpaSpecificationExecutor<Seat> {
}
