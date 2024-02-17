package com.aqazadeh.ecommerce.dto;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:22
 */

public record ProductDto(
        Long id,
        String name,
        String slug,
        String description,
        CategoryDto category,
        List<InventoryDto> inventory,
        DiscountDto discount,
        List<MediaDto> media,
        UserDto seller

) {
}
