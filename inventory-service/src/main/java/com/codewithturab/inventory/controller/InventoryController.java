package com.codewithturab.inventory.controller;

import com.codewithturab.inventory.model.inventory;
import com.codewithturab.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<inventory> getAll() {
        return service.getAll();
    }

    @PostMapping
    public inventory add(@RequestBody inventory item) {
        return service.add(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{name}")
    public inventory getByProductName(@PathVariable String name) {
        return service.findByProduct(name)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
