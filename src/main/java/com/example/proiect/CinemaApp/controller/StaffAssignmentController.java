package com.example.proiect.CinemaApp.controller;

import com.example.proiect.CinemaApp.model.StaffAssignment;
import com.example.proiect.CinemaApp.service.StaffAssignmentService;
import com.example.proiect.CinemaApp.service.ScreeningService;
import com.example.proiect.CinemaApp.service.SupportStaffService;
import com.example.proiect.CinemaApp.service.TechnicalOperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staffassignments")
public class StaffAssignmentController {

    private final StaffAssignmentService staffAssignmentService;
    private final ScreeningService screeningService;
    private final SupportStaffService supportStaffService;
    private final TechnicalOperatorService technicalOperatorService;

    public StaffAssignmentController(StaffAssignmentService staffAssignmentService, ScreeningService screeningService, SupportStaffService supportStaffService, TechnicalOperatorService technicalOperatorService) {
        this.staffAssignmentService = staffAssignmentService;
        this.screeningService = screeningService;
        this.supportStaffService = supportStaffService;
        this.technicalOperatorService = technicalOperatorService;
    }

    @GetMapping
    public String showAssignments(Model model) {
        model.addAttribute("assignments", staffAssignmentService.getAllAssignments());
        return "staffassignment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam(value = "screeningId", required = false) String screeningId, Model model) {
        StaffAssignment sa = new StaffAssignment();
        if (screeningId != null && !screeningId.isBlank()) {
            sa.setScreeningId(screeningId);
        }
        model.addAttribute("assignment", sa);
        model.addAttribute("screenings", screeningService.getAllScreenings());
        List<Object> staff = new ArrayList<>();
        staff.addAll(supportStaffService.getAllSupportStaff());
        staff.addAll(technicalOperatorService.getAllTechnicalOperators());
        model.addAttribute("staffList", staff);
        return "staffassignment/form";
    }

    @PostMapping
    public String addAssignment(@ModelAttribute StaffAssignment assignment) {
        // ensure staff entity is resolved from staffId before saving to avoid DB constraint (StaffId NOT NULL)
        if (assignment.getStaffId() != null && !assignment.getStaffId().isBlank()) {
            String sid = assignment.getStaffId();
            supportStaffService.getSupportStaffById(sid).ifPresentOrElse(
                    st -> assignment.setStaff(st),
                    () -> technicalOperatorService.getTechnicalOperatorById(sid).ifPresent(assignment::setStaff)
            );
        }

        // If staff could not be resolved, redirect back to the form with an error indicator
        if (assignment.getStaff() == null) {
            String screeningParam = (assignment.getScreeningId() != null ? "&screeningId=" + assignment.getScreeningId() : "");
            return "redirect:/staffassignments/new?error=staffNotFound" + screeningParam;
        }

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
        model.addAttribute("screenings", screeningService.getAllScreenings());
        List<Object> staff = new ArrayList<>();
        staff.addAll(supportStaffService.getAllSupportStaff());
        staff.addAll(technicalOperatorService.getAllTechnicalOperators());
        model.addAttribute("staffList", staff);
        return "staffassignment/form-update";
    }

    @PostMapping("/{id}")
    public String updateAssignment(@PathVariable String id, @ModelAttribute StaffAssignment assignment) {
        assignment.setId(id);
        staffAssignmentService.addAssignment(assignment);
        return "redirect:/staffassignments";
    }
}
