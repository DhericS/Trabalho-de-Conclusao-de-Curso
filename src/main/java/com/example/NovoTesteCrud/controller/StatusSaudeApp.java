package com.example.NovoTesteCrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StatusSaudeApp {

    // Endpoint para verificar se o backend está online
    @GetMapping
    public String status() {
        return "<h1>✅ Backend online</h1>";
    }
}