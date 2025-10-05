package com.codewithturab.payment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @GetMapping("/health")
    public String health() {
        return "Payment service is running!";
    }
}
