package com.aqazadeh.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:3.02.2024
 * Time:16:27
 */

public record UpdateUserAddressRequest(
        @JsonProperty("address_line")
        String addressLine,
        String city,
        String country,
        @JsonProperty("postal_code")
        String postalCode
) {
}
