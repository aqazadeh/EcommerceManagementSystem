package com.aqazadeh.ecommerce.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:55
 */
public record CreateInventoryRequest(
        @NotEmpty String name,
        @NotEmpty Integer quantity,

        @NotEmpty Double price
) {

    @Override
    public String toString() {
        return "CreateInventoryRequest{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
