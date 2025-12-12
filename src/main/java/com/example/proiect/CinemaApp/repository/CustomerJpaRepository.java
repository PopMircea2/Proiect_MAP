package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
}
