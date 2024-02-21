package com.aqazadeh.ecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:57
 */

public record UserRegisterRequest(
        @NotEmpty String username,
        @NotEmpty @Email String email,
        @NotEmpty String password,
        @NotEmpty @JsonProperty("first_name") String firstName,
        @NotEmpty @JsonProperty("last_name")  String lastName,
        @NotEmpty String phone
) {
}
