package com.aqazadeh.ecommerce.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:3.02.2024
 * Time:16:26
 */

public record CreateUserAddressRequest(
        @NotEmpty
        @JsonProperty("address_line")
        String addressLine,
        @NotEmpty
        String city,
        @NotEmpty
        String country,
        @NotEmpty
        @JsonProperty("postal_code")
        String postalCode
) {
}
