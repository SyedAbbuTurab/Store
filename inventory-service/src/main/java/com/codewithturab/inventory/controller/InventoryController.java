package com.codewithturab.inventory.controller;

import com.codewithturab.inventory.model.inventory;
import com.codewithturab.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<inventory> getAll() {
        logger.info("📦 Fetching all inventory items");
        List<inventory> items = service.getAll();
        logger.debug("✅ Total items found: {}", items.size());
        return items;
    }

    @PostMapping
    public inventory add(@RequestBody inventory item) {
        // 🧩 Log the raw incoming request body
        logger.info("📥 Received request to add inventory item");
        logger.debug("🧾 Incoming item details: productName='{}', quantity={}, price={}",
                item.getProductName(), item.getQuantity(), item.getPrice());

        inventory saved = service.add(item);
        logger.info("✅ Successfully added inventory item with ID: {}", saved.getId());

        return saved;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        logger.warn("🗑️ Request received to delete item with ID: {}", id);
        service.delete(id);
    }

    @GetMapping("/{name}")
    public inventory getByProductName(@PathVariable String name) {
        logger.info("🔍 Searching inventory for product: {}", name);
        return service.findByProduct(name)
                .orElseThrow(() -> {
                    logger.error("❌ Product '{}' not found in inventory", name);
                    return new RuntimeException("Product not found");
                });
    }
}
