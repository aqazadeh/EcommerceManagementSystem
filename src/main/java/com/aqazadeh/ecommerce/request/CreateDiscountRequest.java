package com.aqazadeh.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:9.02.2024
 * Time:15:54
 */

public record CreateDiscountRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String about,
        @NotEmpty
        @JsonProperty("percent")
        Integer discountPercent,
        @NotEmpty
        @JsonProperty("expire_time")
        LocalDateTime expiredTime
) {
}
