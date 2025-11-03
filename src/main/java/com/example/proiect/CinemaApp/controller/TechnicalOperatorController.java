package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import com.example.proiect.CinemaApp.service.TechnicalOperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/technicaloperators")
public class TechnicalOperatorController {

    private final TechnicalOperatorService technicalOperatorService;

    public TechnicalOperatorController(TechnicalOperatorService technicalOperatorService) {
        this.technicalOperatorService = technicalOperatorService;
    }

    @GetMapping
    public String showTechnicalOperators(Model model) {
        model.addAttribute("technicaloperators", technicalOperatorService.getAllTechnicalOperators());
        return "technicaloperator/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("staff", new TechnicalOperator());
        return "technicaloperator/form";
    }

    @PostMapping
    public String addTechnicalOperator(@ModelAttribute TechnicalOperator staff) {
        technicalOperatorService.addTechnicalOperator(staff);
        return "redirect:/technicaloperators";
    }

    @PostMapping("/{id}/delete")
    public String deleteTechnicalOperator(@PathVariable String id) {
        technicalOperatorService.deleteTechnicalOperator(id);
        return "redirect:/technicaloperators";
    }
}
