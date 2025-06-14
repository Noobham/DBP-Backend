package com.show.bookingApp.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
    private static String secretKey="your-secure-secret-key-must-be-32-bits";
    private static Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String validateAndExtractUsername(String token){
        try{
            return Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (JwtException e){
            return null;
        }
    }

    public String generateToken(String userName , long expiryTime){
        return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiryTime*60*1000))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }
}
