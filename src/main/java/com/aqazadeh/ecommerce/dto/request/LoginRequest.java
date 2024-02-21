package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:55
 */

public record LoginRequest(
        @NotEmpty
        String username,

        @NotEmpty
        String password
) {
}
