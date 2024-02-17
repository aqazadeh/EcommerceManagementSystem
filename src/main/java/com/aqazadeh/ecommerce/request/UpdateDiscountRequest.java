package com.aqazadeh.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:9.02.2024
 * Time:15:52
 */

public record UpdateDiscountRequest(
        String name,
        String about,
        @JsonProperty("percent")
        Integer discountPercent,
        @JsonProperty("expire_time")
        LocalDateTime expiredTime
) {
}
