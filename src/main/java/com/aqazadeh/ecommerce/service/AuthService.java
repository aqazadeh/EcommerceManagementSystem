package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.AuthDto;
import com.aqazadeh.ecommerce.request.LoginRequest;
import com.aqazadeh.ecommerce.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 19:00
 */

public interface AuthService {
    void register(UserRegisterRequest request);
    AuthDto login(LoginRequest request);
    AuthDto refreshToken(String token);

}
