package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.TechnicalOperator;
import com.example.proiect.CinemaApp.service.TechnicalOperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/technicaloperator")
public class TechnicalOperatorController {

    private final TechnicalOperatorService technicalOperatorService;

    public TechnicalOperatorController(TechnicalOperatorService technicalOperatorService) {
        this.technicalOperatorService = technicalOperatorService;
    }

    @GetMapping
    public String showTechnicalOperators(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String specialization,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            Model model) {
        model.addAttribute("technicaloperators", technicalOperatorService.getAllTechnicalOperators(q, specialization, sort, dir));
        model.addAttribute("paramQ", q);
        model.addAttribute("paramSpecialization", specialization);
        model.addAttribute("paramSort", sort);
        model.addAttribute("paramDir", dir);
        model.addAttribute("reverseDir", "asc".equals(dir) ? "desc" : "asc");
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
    public String addTechnicalOperator(@Valid @ModelAttribute TechnicalOperator technicalOperator, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "technicaloperator/form";
        }
        try {
            technicalOperatorService.addTechnicalOperator(technicalOperator);
            return "redirect:/technicaloperator";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add technical operator: " + e.getMessage());
            return "technicaloperator/form";
        }
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
    public String updateTechnicalOperator(@PathVariable String id, @Valid @ModelAttribute TechnicalOperator technicalOperator, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            technicalOperator.setId(id);
            return "technicaloperator/form-update";
        }
        try {
            technicalOperator.setId(id);
            technicalOperatorService.updateTechnicalOperator(technicalOperator);
            return "redirect:/technicaloperator";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update technical operator: " + e.getMessage());
            return "technicaloperator/form-update";
        }
    }
}
