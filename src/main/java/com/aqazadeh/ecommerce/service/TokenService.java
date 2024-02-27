package com.aqazadeh.ecommerce.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:05:29
 */

public interface TokenService {
    String generateToken(String username);

    Boolean validateToken(String token, UserDetails userDetails);

    Claims getClaims(String token);
}
