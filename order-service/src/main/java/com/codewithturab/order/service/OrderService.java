package com.codewithturab.order.service;

import com.codewithturab.order.model.Order;
import com.codewithturab.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getAll() {
        logger.info("📦 Fetching all orders from DB");
        List<Order> orders = repo.findAll();
        logger.debug("Total orders found: {}", orders.size());
        return orders;
    }

    public Optional<Order> getById(String id) {
        logger.info("🔍 Fetching order with ID: {}", id);
        return repo.findById(id);
    }

    public Order create(Order order) {
        logger.info("🧾 Creating new order for product: {}, qty: {}", order.getProductName(), order.getQuantity());
        Order saved = repo.save(order);
        logger.info("✅ Order saved with ID: {}", saved.getId());
        return saved;
    }

    public void delete(String id) {
        logger.warn("🗑️ Deleting order with ID: {}", id);
        repo.deleteById(id);
    }
}
