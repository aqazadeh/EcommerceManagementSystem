package com.aqazadeh.ecommerce.dto.request;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 01:04
 */

public record CreateRatingRequest(
        Long productId,
        Byte rating,
        String message
) {
}
