package com.aqazadeh.ecommerce.dto.request;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:21
 */

public record UpdateProductRequest(
        String name,
        String slug,
        String description,
        Long categoryId
) {
}
