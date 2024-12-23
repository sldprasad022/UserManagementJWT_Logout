package com.techpixe.serviceImpl;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils 
{

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("B2DAC67C5F97687EF3946A876C9CCB736CC34ED879BEF7A92B8DF0749083A098DA2058E41A842C54C0EB424BEE4C16A6D9FE2B0F32E3800CDF3A9C9C48D036CE".getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String email, String role) 
    {
        return Jwts.builder()
                .setSubject(email) // Use email as subject
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    
    public String extractEmail(String token)  // Extract email instead of username
    {
        return extractClaims(token).getSubject();
    }

    
    public String extractRole(String token) 
    {
        return (String) extractClaims(token).get("role");
    }

    
    public boolean isTokenValid(String token) 
    {
        try 
        {
        	System.err.println("**Token is Valid**");
        	
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
            return true;
        } 
        catch (Exception e) 
        {
        	// Log the exception for better troubleshooting
            System.err.println("Token validation failed: " + e.getMessage());
        	
            return false;
        }
    }
}

