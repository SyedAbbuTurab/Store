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
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
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

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User loginRequest) {

        logger.info("Login attempt for user: {}", loginRequest.getUsername());

        User user = userRepo.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw  new RuntimeException("Invalid credentials");
        }

        logger.info("Login attempt for user: {}", loginRequest.getUsername() + "Successful & token generated");

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return  Map.of( "token", token);
    }


}
