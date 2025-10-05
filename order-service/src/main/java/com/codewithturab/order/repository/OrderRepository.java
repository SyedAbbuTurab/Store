package com.codewithturab.order.repository;

import com.codewithturab.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
