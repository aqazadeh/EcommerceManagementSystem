package com.aqazadeh.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:55
 */
public record CreateVariantRequest(
        @NotEmpty String name,
        @NotEmpty Integer quantity,

        @NotEmpty Double price
) {

    @Override
    public String toString() {
        return "CreateVariantRequest{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
