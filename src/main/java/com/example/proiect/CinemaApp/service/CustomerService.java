package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.CustomerRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepo.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepo.add(customer);
    }

    public void deleteCustomerbyId(String id) {
        customerRepo.deleteById(id);
    }
}