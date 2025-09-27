package com.codewithturab.Store.controller;

import com.codewithturab.Store.model.User;
import com.codewithturab.Store.repository.UserRepository;
import com.codewithturab.Store.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        logger.info("📥 Register API called");  // INFO log
        logger.debug("Incoming user object: {}", user); // DEBUG log

        try {
            User saved = userRepo.save(user);
            logger.info("✅ User registered successfully: {}", saved.getUsername());
            return saved;
        } catch (Exception e) {
            logger.error("❌ Failed to register user: {}", e.getMessage(), e);
            throw e;
        }
    }


}
