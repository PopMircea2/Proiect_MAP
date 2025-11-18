package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import org.springframework.stereotype.Repository;

@Repository
public class TechnicalOperatorRepo extends InFileRepository<TechnicalOperator>{
    protected TechnicalOperatorRepo(){
        super(TechnicalOperator::getId, TechnicalOperator.class, "technicaloperators.json");
    }
}
