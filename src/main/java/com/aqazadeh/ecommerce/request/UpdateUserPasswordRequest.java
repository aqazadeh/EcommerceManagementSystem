package com.aqazadeh.ecommerce.request;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:16:55
 */

public record UpdateUserPasswordRequest(
        String oldPassword,
        String newPassword,
        String newRepeatedPassword
) {
}
