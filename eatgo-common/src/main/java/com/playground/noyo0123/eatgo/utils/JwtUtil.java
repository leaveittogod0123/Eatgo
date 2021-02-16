package com.playground.noyo0123.eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {


    private final String secret;

    private Key key;

    public JwtUtil(String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String name, Long restaurantId) {
        // TODO JJWT 사용

        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name);
        if(restaurantId != null) {
            builder = builder.claim("restaurantId", restaurantId);
        }
        String token = builder
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token) // sign이 포함된 jwt
                .getBody();
        return claims;
    }
}
