package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.SupportStaff;
import com.example.proiect.CinemaApp.service.SupportStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/supportstaff")
public class SupportStaffController {

    private final SupportStaffService supportStaffService;

    public SupportStaffController(SupportStaffService supportStaffService) {
        this.supportStaffService = supportStaffService;
    }

    @GetMapping
    public String showSupportStaff(Model model) {
        model.addAttribute("supportstaff", supportStaffService.getAllSupportStaff());
        return "supportstaff/index";
    }

    @GetMapping("/{id}")
    public String showSupportStaffDetails(@PathVariable String id, Model model) {
        supportStaffService.getSupportStaffById(id).ifPresentOrElse(s -> model.addAttribute("supportStaff", s), () -> model.addAttribute("supportStaff", new com.example.proiect.CinemaApp.model.SupportStaff()));
        return "supportstaff/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("supportStaff", new SupportStaff());
        return "supportstaff/form";
    }

    @PostMapping
    public String addSupportStaff(@Valid @ModelAttribute SupportStaff staff, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "supportstaff/form";
        }
        try {
            supportStaffService.addSupportStaff(staff);
            return "redirect:/supportstaff";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add support staff: " + e.getMessage());
            return "supportstaff/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteSupportStaff(@PathVariable String id) {
        supportStaffService.deleteSupportStaffbyId(id);
        return "redirect:/supportstaff";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        supportStaffService.getSupportStaffById(id).ifPresentOrElse(s -> model.addAttribute("supportStaff", s), () -> model.addAttribute("supportStaff", new SupportStaff()));
        return "supportstaff/form-update";
    }

    @PostMapping("/{id}")
    public String updateSupportStaff(@PathVariable String id, @Valid @ModelAttribute SupportStaff staff, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            staff.setId(id);
            return "supportstaff/form-update";
        }
        try {
            staff.setId(id);
            supportStaffService.addSupportStaff(staff);
            return "redirect:/supportstaff";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update support staff: " + e.getMessage());
            return "supportstaff/form-update";
        }
    }
}
