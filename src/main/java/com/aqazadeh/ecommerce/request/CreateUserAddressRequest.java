package com.aqazadeh.ecommerce.request;

import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:3.02.2024
 * Time:16:26
 */

public record CreateUserAddressRequest(
        @NotEmpty
        String addressLine,
        @NotEmpty
        String city,
        @NotEmpty
        String country,
        @NotEmpty
        String postalCode
) {
}
