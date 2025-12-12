package com.example.proiect.CinemaApp.service;


import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.repository.CustomerJpaRepository;
import com.example.proiect.CinemaApp.exception.BusinessValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

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

    // New: filterable + sortable getAllCustomers
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers(String q, String sortBy, String sortDir) {
        Sort sort = Sort.by((sortBy == null || sortBy.isBlank()) ? "id" : sortBy);
        if ("desc".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        Specification<Customer> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (q != null && !q.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + q.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return customerRepo.findAll(spec, sort);
    }

    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerById(String id) {
        Optional<Customer> opt = customerRepo.findById(id);
        opt.ifPresent(c -> { if (c.getTickets() != null) c.getTickets().size(); });
        return opt;
    }

    public Customer addCustomer(Customer customer) {
        if (customer.getId() == null || customer.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (customerRepo.existsById(customer.getId())) {
            throw new BusinessValidationException("A customer with ID '" + customer.getId() + "' already exists");
        }
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        if (customer.getId() == null || customer.getId().isBlank()) {
            throw new BusinessValidationException("ID is required and cannot be empty");
        }
        if (!customerRepo.existsById(customer.getId())) {
            throw new BusinessValidationException("Customer with ID '" + customer.getId() + "' does not exist");
        }
        return customerRepo.save(customer);
    }

    public void deleteCustomerbyId(String id) {
        customerRepo.deleteById(id);
    }
}