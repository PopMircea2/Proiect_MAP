package com.example.proiect.CinemaApp.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessValidationException.class)
    public String handleBusinessValidationException(BusinessValidationException ex, Model model) {
        model.addAttribute("errorTitle", "Business Validation Error");
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/business-error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorTitle", "Data Integrity Error");
        String message = "A database constraint was violated. ";
        if (ex.getMessage().contains("Duplicate entry")) {
            message += "The record already exists.";
        } else {
            message += "Please check your input and try again.";
        }
        model.addAttribute("errorMessage", message);
        return "error/business-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorTitle", "Unexpected Error");
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return "error/business-error";
    }
}
