package com.example.zCartBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "Welcome to the dashboard!";
    }
}