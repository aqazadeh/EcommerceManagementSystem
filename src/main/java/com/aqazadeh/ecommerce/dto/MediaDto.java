package com.aqazadeh.ecommerce.dto;

import com.aqazadeh.ecommerce.model.enums.MediaType;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:53
 */

public record MediaDto(
        Long id,
        MediaType mediaType,
        String url
) {
}
