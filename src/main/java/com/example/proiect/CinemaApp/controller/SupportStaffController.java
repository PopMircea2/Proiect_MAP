package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.SupportStaff;
import com.example.proiect.CinemaApp.service.SupportStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/supportstaffs")
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
        model.addAttribute("staff", new SupportStaff());
        return "supportstaff/form";
    }

    @PostMapping
    public String addSupportStaff(@ModelAttribute SupportStaff staff) {
        supportStaffService.addSupportStaff(staff);
        return "redirect:/supportstaffs";
    }

    @PostMapping("/{id}/delete")
    public String deleteSupportStaff(@PathVariable String id) {
        supportStaffService.deleteSupportStaff(id);
        return "redirect:/supportstaffs";
    }
}
