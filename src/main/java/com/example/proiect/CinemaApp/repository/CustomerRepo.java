package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Customer;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CustomerRepo {
    private final Map<String, Customer> customers = new HashMap<>();

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customers.get(id));
    }

    public Customer save(Customer customer) {
        customers.put(customer.getId(), customer);
        return customer;
    }

    public void deleteById(String id) {
        customers.remove(id);
    }
}

