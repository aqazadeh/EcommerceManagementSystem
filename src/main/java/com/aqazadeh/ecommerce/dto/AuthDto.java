package com.aqazadeh.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:51
 */

public record AuthDto(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken
) {
}
