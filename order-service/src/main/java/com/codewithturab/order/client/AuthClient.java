package com.codewithturab.order.client;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

@Component
public class AuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String authServiceUrl = "http://localhost:8080/api/auth/validate";

    public Boolean validateToken(String token) {
        try {
            ResponseEntity<Boolean> response = restTemplate
                    .postForEntity(authServiceUrl, token, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (Exception e) {
            System.err.println("Failed to validate token: " + e.getMessage());
            return false;
        }
        }
    }

