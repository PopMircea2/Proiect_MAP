package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.CustomerJpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerJpaRepository customerRepo;

    public CustomerService(CustomerJpaRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepo.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        if (customer.getId() == null || customer.getId().isBlank()) {
            customer.setId(UUID.randomUUID().toString());
        }
        return customerRepo.save(customer);
    }

    public void deleteCustomerbyId(String id) {
        customerRepo.deleteById(id);
    }
}