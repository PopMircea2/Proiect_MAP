package com.example.proiect.CinemaApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


    @org.springframework.stereotype.Controller
    public class Controller {
        @GetMapping("/hello")
        @ResponseBody
        public String sayHello() {
            return "Die Anwendung funktioniert!";
        }
    }

