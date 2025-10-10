package com.codewithturab.inventory.service;

import com.codewithturab.inventory.model.inventory;
import com.codewithturab.inventory.repository.inventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InventoryService {

    private final inventoryRepository repo;

    public InventoryService(inventoryRepository repo) {
        this.repo = repo;
    }

    public List<inventory>getAll() {
        return repo.findAll();
    }

    public inventory add(inventory inventory) {
        return repo.save(inventory);
    }

    public Optional<inventory> findByProduct(String name) {
        return repo.findByProductName(name);
    }

//    public void delete(String id) {
//        repo.deleteById(id);
//    }
}
