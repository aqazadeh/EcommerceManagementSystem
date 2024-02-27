package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateCouponRequest;
import com.aqazadeh.ecommerce.dto.response.CouponDto;
import com.aqazadeh.ecommerce.model.Coupon;
import com.aqazadeh.ecommerce.model.User;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:17:41
 */

public interface CouponService {
    void create(User user, CreateCouponRequest request);

    void update(User user, Long id, UpdateCouponRequest request);

    CouponDto getById(Long id);

    CouponDto getByCode(String code);

    void delete(User user, Long id);

    Coupon findById(Long id);

    Coupon findByCode(String code);
}
