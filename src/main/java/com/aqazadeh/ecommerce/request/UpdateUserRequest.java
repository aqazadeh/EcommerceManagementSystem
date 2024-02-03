package com.aqazadeh.ecommerce.request;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:16:40
 */

public record UpdateUserRequest(
        String firstName,
        String lastName,
        String phone
) {
}
