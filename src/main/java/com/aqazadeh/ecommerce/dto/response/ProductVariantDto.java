package com.aqazadeh.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:53
 */

public record ProductVariantDto(
        Long id,
        String name,
        Integer quantity,
        Double price
) {
}
