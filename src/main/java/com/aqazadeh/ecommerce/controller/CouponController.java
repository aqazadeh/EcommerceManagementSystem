package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.CouponDto;
import com.aqazadeh.ecommerce.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.request.UpdateCouponRequest;
import com.aqazadeh.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 17:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class CouponController {
    private final CouponService couponService;
    @PostMapping
    public ResponseEntity<Void> createCoupon(@RequestBody CreateCouponRequest request) {
        couponService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody UpdateCouponRequest request){
        couponService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CouponDto>> getAll(@RequestParam Integer page) {
        List<CouponDto> couponDtoList = couponService.getAll(page);
        return ResponseEntity.ok(couponDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDto> getById(@PathVariable Long id) {
        CouponDto couponDtoList = couponService.getById(id);
        return ResponseEntity.ok(couponDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,@RequestBody UpdateCouponRequest request){
        couponService.delete(id, request);
        return ResponseEntity.noContent().build();
    }
}
