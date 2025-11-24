package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.CustomerJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerJpaRepository customerRepo;

    public CustomerService(CustomerJpaRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        List<Customer> list = customerRepo.findAll();
        for (Customer c : list) {
            if (c.getTickets() != null) c.getTickets().size();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerById(String id) {
        Optional<Customer> opt = customerRepo.findById(id);
        opt.ifPresent(c -> { if (c.getTickets() != null) c.getTickets().size(); });
        return opt;
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