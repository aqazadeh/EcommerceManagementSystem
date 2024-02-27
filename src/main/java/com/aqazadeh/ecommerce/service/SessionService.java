package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.response.AuthDto;
import com.aqazadeh.ecommerce.model.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 26.02.2024
 * Time: 23:29
 */

public interface SessionService {

    AuthDto create(User user, HttpServletRequest request);

    AuthDto refreshToken(String token, HttpServletRequest request);
}
