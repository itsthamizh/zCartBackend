package com.example.zCartBackend.controller;

import com.example.zCartBackend.model.Inventories;
import com.example.zCartBackend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;
    @GetMapping("/list-all-inventory")
    public List<Inventories> getAllUsers() {
        return inventoryRepository.findAll();
    }
}
