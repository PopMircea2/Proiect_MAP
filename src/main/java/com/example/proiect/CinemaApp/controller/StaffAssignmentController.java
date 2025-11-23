package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import com.example.proiect.CinemaApp.service.StaffAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staffassignments")
public class StaffAssignmentController {

    private final StaffAssignmentService staffAssignmentService;

    public StaffAssignmentController(StaffAssignmentService staffAssignmentService) {
        this.staffAssignmentService = staffAssignmentService;
    }

    @GetMapping
    public String showAssignments(Model model) {
        model.addAttribute("assignments", staffAssignmentService.getAllAssignments());
        return "staffassignment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new StaffAssignment());
        return "staffassignment/form";
    }

    @PostMapping
    public String addAssignment(@ModelAttribute StaffAssignment assignment) {
        staffAssignmentService.addAssignment(assignment);
        return "redirect:/staffassignments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable String id) {
        staffAssignmentService.deleteAssignmentbyId(id);
        return "redirect:/staffassignments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        staffAssignmentService.getAssignmentById(id).ifPresentOrElse(a -> model.addAttribute("assignment", a), () -> model.addAttribute("assignment", new StaffAssignment()));
        return "staffassignment/form-update";
    }

    @PostMapping("/{id}")
    public String updateAssignment(@PathVariable String id, @ModelAttribute StaffAssignment assignment) {
        assignment.setId(id);
        staffAssignmentService.addAssignment(assignment);
        return "redirect:/staffassignments";
    }
}
