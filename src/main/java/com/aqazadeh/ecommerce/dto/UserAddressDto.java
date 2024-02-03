package com.aqazadeh.ecommerce.dto;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:3.02.2024
 * Time:16:23
 */

public record UserAddressDto(
        Long id,
        String addressLine,
        String city,
        String country,
        String postalCode
) {
}
