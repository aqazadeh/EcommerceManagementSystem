package com.aqazadeh.ecommerce.dto.response;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:19.02.2024
 * Time:01:43
 */

public record CartDto(
        Long id,
        Integer quantity,
        ProductVariantDto variant,
        ProductDto product
) {
}
