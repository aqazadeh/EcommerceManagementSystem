package com.aqazadeh.ecommerce.request;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:3.02.2024
 * Time:16:27
 */

public record UpdateUserAddressRequest(
        String addressLine,
        String city,
        String country,
        String postalCode
) {
}
