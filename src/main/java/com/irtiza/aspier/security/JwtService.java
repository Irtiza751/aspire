package com.irtiza.aspier.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(Map<String, String> claims, UserDetails userDetails);
    boolean isValidToken(String token, UserDetails userDetails);
    <T> T extractClaim(String token, Function<Claims, T> claimResolver);
}