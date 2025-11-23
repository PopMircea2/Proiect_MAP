package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Customer;

public class CustomerRepo extends InFileRepository<Customer>{

    protected CustomerRepo() {
        super(c -> c.getId() == null ? null : c.getId().toString(), Customer.class, "customers.json");
    }
}
