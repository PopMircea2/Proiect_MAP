package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo extends InFileRepository<Customer>{

    protected CustomerRepo() {
        super(Customer::getId, Customer.class, "customers.json");
    }
}
