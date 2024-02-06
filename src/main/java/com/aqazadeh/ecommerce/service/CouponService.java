package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.CouponDto;
import com.aqazadeh.ecommerce.model.Coupon;
import com.aqazadeh.ecommerce.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.request.UpdateCouponRequest;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:17:41
 */

public interface CouponService {
    void create(CreateCouponRequest request);

    void update(Long id, UpdateCouponRequest request);

    List<CouponDto> getAll(Integer page);

    CouponDto getById(Long id);

    void delete(Long id, UpdateCouponRequest request);

    Coupon getByCode(String code);
}
