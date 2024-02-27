package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateCouponRequest;
import com.aqazadeh.ecommerce.dto.response.CouponDto;
import com.aqazadeh.ecommerce.model.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 18:01
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CouponMapper {
    CouponDto toDto(Coupon coupon);

    Coupon toEntity(CreateCouponRequest request);

    Coupon toEntity(@MappingTarget Coupon coupon, UpdateCouponRequest request);
}
