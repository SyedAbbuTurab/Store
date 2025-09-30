package com.codewithturab.Store.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


public class JwtUtil {

    @Value("${jwt.secret}")
    private static String secretKey;

    private static  final long EXPIRATION = 1000 * 60 * 60;

    public static  String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public static String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public static Boolean isTokenValid(String token) {
        return !getClaims(token).getExpiration().before(new Date());
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

}
