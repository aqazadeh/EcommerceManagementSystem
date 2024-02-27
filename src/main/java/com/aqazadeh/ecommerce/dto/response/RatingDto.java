package com.aqazadeh.ecommerce.dto.response;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 00:54
 */

public record RatingDto(
        Long id,
        UserDto user,
        Byte rating,
        List<MediaDto> media,
        String message
) {
}
