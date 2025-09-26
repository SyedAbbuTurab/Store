package com.codewithturab.Store.controller;

import com.codewithturab.Store.model.User;
import com.codewithturab.Store.repository.UserRepository;
import com.codewithturab.Store.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

}
