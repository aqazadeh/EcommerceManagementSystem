package com.aqazadeh.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:3.02.2024
 * Time:16:23
 */

public record UserAddressDto(
        @JsonProperty("address_id") Long id,
        @JsonProperty("address_line") String addressLine,
        String city,
        String country,
        @JsonProperty("postal_code") String postalCode
) {
}
