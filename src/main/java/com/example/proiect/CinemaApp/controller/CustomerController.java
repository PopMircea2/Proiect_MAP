package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customer/index";
    }

    // Show details for a specific customer
    @GetMapping("/{id}")
    public String showCustomerDetails(@PathVariable String id, Model model) {
        customerService.getCustomerById(id).ifPresentOrElse(c -> model.addAttribute("customer", c), () -> model.addAttribute("customer", new com.example.proiect.CinemaApp.model.Customer()));
        return "customer/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @PostMapping
    public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "customer/form";
        }
        try {
            customerService.addCustomer(customer);
            return "redirect:/customers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add customer: " + e.getMessage());
            return "customer/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomerbyId(id);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        customerService.getCustomerById(id).ifPresentOrElse(c -> model.addAttribute("customer", c), () -> model.addAttribute("customer", new Customer()));
        return "customer/form-update";
    }

    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable String id, @Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            customer.setId(id);
            return "customer/form-update";
        }
        try {
            customer.setId(id);
            customerService.addCustomer(customer);
            return "redirect:/customers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update customer: " + e.getMessage());
            return "customer/form-update";
        }
    }
}
