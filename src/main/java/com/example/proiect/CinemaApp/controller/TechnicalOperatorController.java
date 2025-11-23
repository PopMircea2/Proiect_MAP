package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import com.example.proiect.CinemaApp.service.TechnicalOperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/technicaloperator")
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

    @GetMapping("/{id}")
    public String showTechnicalOperatorDetails(@PathVariable String id, Model model) {
        technicalOperatorService.getTechnicalOperatorById(id).ifPresentOrElse(t -> model.addAttribute("technicalOperator", t), () -> model.addAttribute("technicalOperator", new com.example.proiect.CinemaApp.model.TechnicalOperator()));
        return "technicaloperator/show";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("technicalOperator", new TechnicalOperator());
        return "technicaloperator/form";
    }

    @PostMapping
    public String addTechnicalOperator(@ModelAttribute TechnicalOperator technicalOperator) {
        technicalOperatorService.addTechnicalOperator(technicalOperator);
        return "redirect:/technicaloperator";
    }

    @PostMapping("/{id}/delete")
    public String deleteTechnicalOperator(@PathVariable String id) {
        technicalOperatorService.deleteTechnicalOperatorbyId(id);
        return "redirect:/technicaloperator";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        technicalOperatorService.getTechnicalOperatorById(id).ifPresentOrElse(t -> model.addAttribute("technicalOperator", t), () -> model.addAttribute("technicalOperator", new TechnicalOperator()));
        return "technicaloperator/form-update";
    }

    @PostMapping("/{id}")
    public String updateTechnicalOperator(@PathVariable String id, @ModelAttribute TechnicalOperator technicalOperator) {
        technicalOperator.setId(id);
        technicalOperatorService.addTechnicalOperator(technicalOperator);
        return "redirect:/technicaloperator";
    }
}
