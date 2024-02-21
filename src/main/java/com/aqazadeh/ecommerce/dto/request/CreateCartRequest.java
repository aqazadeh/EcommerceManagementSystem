package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:20.02.2024
 * Time:20:02
 */

public record CreateCartRequest(
        @NotNull Long productId,
        @NotNull Long variantId,
        @NotNull Integer quantity
) {
}
