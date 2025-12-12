package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalOperatorJpaRepository extends JpaRepository<TechnicalOperator, String>, JpaSpecificationExecutor<TechnicalOperator> {
}
