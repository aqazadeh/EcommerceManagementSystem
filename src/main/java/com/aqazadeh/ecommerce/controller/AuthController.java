package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.LoginRequest;
import com.aqazadeh.ecommerce.dto.request.ResetPasswordRequest;
import com.aqazadeh.ecommerce.dto.request.UserRegisterRequest;
import com.aqazadeh.ecommerce.dto.response.AuthDto;
import com.aqazadeh.ecommerce.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity<AuthDto> login(@RequestBody @Valid LoginRequest request, HttpServletRequest servletRequest) {
        AuthDto login = authService.login(request, servletRequest);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/register")
    private ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh-token/{token}")
    private ResponseEntity<AuthDto> refreshToken(@PathVariable String token, HttpServletRequest request) {
        AuthDto refreshDto = authService.refreshToken(token, request);
        return ResponseEntity.ok(refreshDto);
    }

    @GetMapping("/activate")
    private ResponseEntity<Void> activateToken(@RequestParam String token) {
        authService.activate(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/forget-password")
    private ResponseEntity<Void> resetPassword(@RequestParam String username) {
        authService.resetPassword(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    private ResponseEntity<AuthDto> resetPasswordConfirm(@RequestBody @Valid ResetPasswordRequest request) {
        authService.resetPasswordConfirm(request);
        return ResponseEntity.ok().build();
    }
}
