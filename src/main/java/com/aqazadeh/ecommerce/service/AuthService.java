package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.ResetPasswordRequest;
import com.aqazadeh.ecommerce.dto.response.AuthDto;
import com.aqazadeh.ecommerce.dto.request.LoginRequest;
import com.aqazadeh.ecommerce.dto.request.UserRegisterRequest;

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

    void activate(String token);

    void resetPassword(String username);

    void resetPasswordConfirm(ResetPasswordRequest request);
}
