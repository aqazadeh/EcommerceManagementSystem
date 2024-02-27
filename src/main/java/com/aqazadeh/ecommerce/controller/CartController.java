package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.CreateCartRequest;
import com.aqazadeh.ecommerce.dto.response.CartDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 19.02.2024
 * Time: 01:38
 */
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping
    public ResponseEntity<Void> addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateCartRequest request
    ) {
        service.addToCart(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getUserCart(@AuthenticationPrincipal User user) {
        List<CartDto> cartDtoList = service.getUserCart(user);
        return ResponseEntity.ok(cartDtoList);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removeFromCart(@AuthenticationPrincipal User user, @PathVariable Long itemId) {
        service.removeFromCart(user, itemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User user) {
        service.clearCart(user);
        return ResponseEntity.ok().build();
    }
}
