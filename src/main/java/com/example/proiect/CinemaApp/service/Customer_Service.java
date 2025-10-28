package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.Customer_Repo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class Customer_Service {
    private final Customer_Repo customerRepo;

    public Customer_Service(Customer_Repo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepo.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }
}