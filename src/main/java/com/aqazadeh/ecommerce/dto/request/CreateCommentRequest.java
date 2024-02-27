package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 17:55
 */

public record CreateCommentRequest(
        @NotNull Long productId,
        Long parentId,
        @NotBlank String message
) {
}
