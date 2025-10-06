package com.codewithturab.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.codewithturab.order.model.Order;
import com.codewithturab.order.repository.OrderRepository;
import com.codewithturab.order.client.AuthClient;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderRepository repo;
    private final AuthClient authClient;

    public OrderController(OrderRepository repo, AuthClient authClient) {
        this.repo = repo;
        this.authClient = authClient;
    }

    @GetMapping
    public List<Order> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        logger.info(order.getProductName());
        return repo.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
    @GetMapping("/test")
    public String test(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        boolean valid = authClient.validateToken(token);
        return valid ? "Token is valid. Order service is working!"
                : "Invalid token!";
    }

}
