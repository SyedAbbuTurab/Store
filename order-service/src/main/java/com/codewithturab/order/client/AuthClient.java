package com.codewithturab.order.client;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class AuthClient {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean validateToken(String token) {
        Map<String, Object> response = restTemplate.postForObject(
                authServiceUrl + "/validate",
                Map.of("token", token),
                Map.class
        );
        return response != null && Boolean.TRUE.equals(response.get("valid"));
    }
}

