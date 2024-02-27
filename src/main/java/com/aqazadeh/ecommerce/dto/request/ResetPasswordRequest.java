package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:22.02.2024
 * Time:00:56
 */

public record ResetPasswordRequest(
        @NotBlank String token,
        @NotBlank @Min(8) String password,
        @NotEmpty @Min(8) String passwordConfirm
) {
}
