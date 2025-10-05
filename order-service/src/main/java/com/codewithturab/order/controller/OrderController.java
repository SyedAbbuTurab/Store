package com.codewithturab.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.codewithturab.order.model.Order;
import com.codewithturab.order.repository.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
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

}
