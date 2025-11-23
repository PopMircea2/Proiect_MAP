package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.SupportStaff;
import com.example.proiect.CinemaApp.service.SupportStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("supportStaff", new SupportStaff());
        return "supportstaff/form";
    }

    @PostMapping
    public String addSupportStaff(@ModelAttribute SupportStaff staff) {
        supportStaffService.addSupportStaff(staff);
        return "redirect:/supportstaff";
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
    public String updateSupportStaff(@PathVariable String id, @ModelAttribute SupportStaff staff) {
        staff.setId(id);
        supportStaffService.addSupportStaff(staff);
        return "redirect:/supportstaff";
    }
}
