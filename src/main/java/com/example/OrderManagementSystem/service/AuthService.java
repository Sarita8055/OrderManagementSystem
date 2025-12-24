package com.example.OrderManagementSystem.service;

import io.jsonwebtoken.Jwts;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.util.*;

@Service
public class AuthService {
	 private static final String SECRET =
	            "mysecretkeymysecretkeymysecretkey123456"; // min 32 chars

	    private Key getSignKey() {
	        return Keys.hmacShaKeyFor(SECRET.getBytes());
	    }

	    public String generateToken(String email, String role) {

	        Map<String, Object> claims = new HashMap<>();
	        claims.put("role", role);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(email)
	                .setIssuedAt(new Date())
	                .setExpiration(
	                        new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)
	                )
	                .signWith(getSignKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public String extractEmail(String token) {
	        return extractAllClaims(token).getSubject();
	    }

	    public String extractRole(String token) {
	        return extractAllClaims(token).get("role", String.class);
	    }

	    public boolean validateToken(String token, String email) {
	        return extractEmail(token).equals(email) &&
	               !extractAllClaims(token).getExpiration().before(new Date());
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
}
