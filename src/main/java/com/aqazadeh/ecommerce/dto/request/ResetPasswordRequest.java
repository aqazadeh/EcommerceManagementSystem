package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:22.02.2024
 * Time:00:56
 */

public record ResetPasswordRequest(
        @NotEmpty String token,
        @NotEmpty @Size(min = 8) String password,
        @NotEmpty @Size(min = 8) String passwordConfirm
) {
}
