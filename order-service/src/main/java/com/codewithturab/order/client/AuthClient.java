package com.codewithturab.order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthClient {

    private static final Logger logger = LoggerFactory.getLogger(AuthClient.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${auth.service.url}")
    private String authServiceUrl; // e.g., http://localhost:8080/api/auth/validate

    public boolean validateToken(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    authServiceUrl, HttpMethod.GET, entity, Map.class
            );

            boolean valid = Boolean.TRUE.equals(response.getBody().get("valid"));
            logger.info("Token validation result from Auth service: {}", valid);
            return valid;

        } catch (Exception e) {
            logger.error("Auth validation failed: {}", e.getMessage());
            return false;
        }
    }
}
