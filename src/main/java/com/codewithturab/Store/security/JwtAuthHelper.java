package com.codewithturab.Store.security;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthHelper {

    private final JwtUtil jwtUtil;

    public JwtAuthHelper(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
}
