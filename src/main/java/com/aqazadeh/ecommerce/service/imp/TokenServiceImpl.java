package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 05:28
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Value("${jwt.key}")
    private String key;


    @Override
    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .subject(username)
//                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + (7200 * 1000)))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey())
                .compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        Claims claims = getClaims(token);
        String username = claims.getSubject();
        Date expireDate = claims.getExpiration();
        return userDetails.getUsername().equals(username) && !expireDate.before(new Date());
    }

    private Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyByte);
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
