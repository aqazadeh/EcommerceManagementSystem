package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.AuthDto;
import com.aqazadeh.ecommerce.request.LoginRequest;
import com.aqazadeh.ecommerce.request.UserRegisterRequest;
import com.aqazadeh.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 18:49
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    private ResponseEntity<AuthDto> login(@Validated @RequestBody LoginRequest request) {
        AuthDto login = authService.login(request);
        return ResponseEntity.ok(login);
    }
    @PostMapping("/register")
    private ResponseEntity<Void> register(@Validated @RequestBody UserRegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/refresh-token/{token}")
    private ResponseEntity<AuthDto> refreshToken(@PathVariable String token) {
        AuthDto refreshDto = authService.refreshToken(token);
        return ResponseEntity.ok(refreshDto);
    }
}
