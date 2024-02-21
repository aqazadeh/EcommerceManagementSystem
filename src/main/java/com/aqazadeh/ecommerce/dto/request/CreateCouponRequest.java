package com.aqazadeh.ecommerce.dto.request;

import com.aqazadeh.ecommerce.model.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:17:41
 */

public record CreateCouponRequest(
        @NotEmpty
        @JsonProperty("type")
        CouponType couponType,
        @JsonProperty("percent_count")
        Integer percentCount,
        @JsonProperty("discount_count")
        Double cashCount,
        @NotEmpty
        String code,
        @NotEmpty
        String name,
        @NotEmpty
        String about,
        @NotEmpty
        @JsonProperty("expire_time")
        LocalDateTime expireTime
) {
}
