package com.codewithturab.Store.security;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthHelper {

    private final JwtUtil jwtUtil;

    public JwtAuthHelper(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Extracts the raw token string from the Authorization header.
     * @param authHeader the value of the Authorization header
     * @return the raw token string without the "Bearer " prefix
     */

    public String extractToken(String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw  new RuntimeException("Missing or malformed Authorization header");
        }
        return authHeader.substring(7);
    }
    /**
     * Validates the JWT token and returns the username.
     * @param token the JWT token string
     * @return the username encoded in the token
     */
    public String validateAndExtractUsername (String token) {
        if(jwtUtil.isTokenValid(token)) {
            throw new RuntimeException("Invalid or Expired token");
        }
        return jwtUtil.extractUsername(token);
    }

    /**
     * Validates the JWT token and returns the role.
     * @param token the JWT token string
     * @return the role claim encoded in the token
     */


}
