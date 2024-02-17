package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.CouponDto;
import com.aqazadeh.ecommerce.model.Coupon;
import com.aqazadeh.ecommerce.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.request.UpdateCouponRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 18:01
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CouponMapper {
    CouponDto toCouponDto(Coupon coupon);

    Coupon toCoupon(CreateCouponRequest request);

    Coupon toCoupon(@MappingTarget Coupon coupon, UpdateCouponRequest request);
}
