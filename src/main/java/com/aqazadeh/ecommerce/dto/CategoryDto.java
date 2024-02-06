package com.aqazadeh.ecommerce.dto;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:15:26
 */

public record CategoryDto(
        Long id,
        String slug,
        String name,
        String about,
        List<CategoryDto> children
) {
}
