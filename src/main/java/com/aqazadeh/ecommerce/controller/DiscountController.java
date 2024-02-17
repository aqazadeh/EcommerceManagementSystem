package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.DiscountDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.request.UpdateDiscountRequest;
import com.aqazadeh.ecommerce.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 9.02.2024
 * Time: 15:35
 */
@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDiscount(@PathVariable Long id, @RequestBody UpdateDiscountRequest request) {
        discountService.update(id, request);
        return ResponseEntity.ok().build();
    }
}
