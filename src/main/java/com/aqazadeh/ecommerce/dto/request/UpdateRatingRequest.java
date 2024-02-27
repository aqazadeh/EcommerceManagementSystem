package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 01:05
 */

public record UpdateRatingRequest(
        @NotEmpty String message
) {
}
