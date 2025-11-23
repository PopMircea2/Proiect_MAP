package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.Customer;
import com.example.proiect.CinemaApp.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @PostMapping
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers";
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
    public String updateCustomer(@PathVariable String id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }
}
