package com.codewithturab.inventory.repository;

import com.codewithturab.inventory.model.inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

// Helps to use Mongo Methods by extending the model to library.
public interface inventoryRepository extends MongoRepository<inventory, String> {
    Optional<inventory> findByProductName(String productName);
}
