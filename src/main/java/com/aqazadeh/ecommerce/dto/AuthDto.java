package com.aqazadeh.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:51
 */

public record AuthDto(

        String accessToken,
        String refreshToken
) {
}
