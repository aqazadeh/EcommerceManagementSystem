package com.aqazadeh.ecommerce.dto.response;

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
        List<ProductVariantDto> variants,
        DiscountDto discount,
        List<MediaDto> media,
        UserDto seller

) {
}
