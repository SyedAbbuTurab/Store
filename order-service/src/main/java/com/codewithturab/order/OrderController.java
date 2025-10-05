package com.codewithturab.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/health")
    public String health() {
        logger.info("✅ Order service is up");
        return "Order service is running!";
    }
}
