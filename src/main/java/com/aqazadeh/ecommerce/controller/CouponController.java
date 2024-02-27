package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateCouponRequest;
import com.aqazadeh.ecommerce.dto.response.CouponDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 17:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
public class CouponController {
    private final CouponService service;

    @PostMapping
    @PreAuthorize("hasRole('USER') and hasRole('SELLER')")
    public ResponseEntity<Void> createCoupon(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateCouponRequest request
    ) {
        service.create(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER') and hasRole('SELLER')")
    public ResponseEntity<Void> update(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody UpdateCouponRequest request
    ) {
        service.update(user, id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDto> getById(@PathVariable Long id) {
        CouponDto couponDtoList = service.getById(id);
        return ResponseEntity.ok(couponDtoList);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CouponDto> getByCode(@PathVariable String code) {
        CouponDto couponDtoList = service.getByCode(code);
        return ResponseEntity.ok(couponDtoList);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') and hasRole('SELLER')")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        service.delete(user, id);
        return ResponseEntity.noContent().build();
    }
}
