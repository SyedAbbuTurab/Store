package com.codewithturab.Store.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;

public class JwtUtil {
    private static  final String SECRET_KEY = "Crazpeeps@1";
    private static  final long EXPIRATION = 1000 * 60 * 60;


}
