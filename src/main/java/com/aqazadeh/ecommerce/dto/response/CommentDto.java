package com.aqazadeh.ecommerce.dto.response;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 18:12
 */

public record CommentDto(
        Long id,
        String message,
        UserDto user,
        List<CommentDto> children
) {

}
