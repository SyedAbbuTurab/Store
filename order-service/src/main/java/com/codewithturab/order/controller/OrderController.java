package com.codewithturab.order.controller;

import com.codewithturab.order.model.Order;
import com.codewithturab.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // 🧾 Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        logger.info("📦 [GET] Request received → /api/orders");
        List<Order> orders = service.getAll();
        return ResponseEntity.ok(orders);
    }

    // 🔍 Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        logger.info("🔍 [GET] Fetching order by ID: {}", id);
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("⚠️ Order not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // 🧱 Create new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        logger.info("🧾 [POST] Creating order for product: {} (qty: {})",
                order.getProductName(), order.getQuantity());

        if (order.getProductName() == null || order.getProductName().isBlank()) {
            logger.error("❌ Invalid order: Product name is empty");
            return ResponseEntity.badRequest().build();
        }

        Order saved = service.create(order);
        logger.info("✅ Order created successfully with ID: {}", saved.getId());
        return ResponseEntity.ok(saved);
    }

    // 🗑️ Delete order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        logger.warn("🗑️ [DELETE] Deleting order ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
