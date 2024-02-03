package com.aqazadeh.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:16:55
 */

public record UpdateUserPasswordRequest(
        @JsonProperty("old_password")
        String oldPassword,
        @JsonProperty("new_password")
        String newPassword,
        @JsonProperty("new_repeated_password")
        String newRepeatedPassword
) {
}
