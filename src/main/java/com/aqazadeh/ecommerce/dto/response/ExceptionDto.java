package com.aqazadeh.ecommerce.dto.response;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:20.02.2024
 * Time:17:47
 */

public record ExceptionDto(
        Integer status,
        List<String> message
) {
}
