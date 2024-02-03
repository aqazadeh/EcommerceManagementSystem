package com.aqazadeh.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:14:52
 */

public record UserDto(
        @JsonProperty("user_id")
        Long id,
        String username,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        String phone
) {
}
