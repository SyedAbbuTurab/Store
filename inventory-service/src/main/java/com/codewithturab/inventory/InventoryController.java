package com.codewithturab.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @GetMapping("/inventory/health")
    public String health() {
        return "✅ Inventory Service is running!";
    }
}
