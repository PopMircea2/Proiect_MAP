package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo extends MemoryRepo<Customer>{


    protected CustomerRepo() {
        super(Customer::getId);
    }
}

