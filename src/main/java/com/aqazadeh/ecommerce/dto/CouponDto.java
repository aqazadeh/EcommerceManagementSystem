package com.aqazadeh.ecommerce.dto;

import com.aqazadeh.ecommerce.model.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:17:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CouponDto(
        Long id,
        @JsonProperty("type")
        CouponType couponType,
        @JsonProperty("percent_count")
        Integer percentCount,
        @JsonProperty("discount_count")
        Double cashCount,
        String code,
        String name,
        String about,
        @JsonProperty("expire_time")
        LocalDateTime expireTime
) {
}
