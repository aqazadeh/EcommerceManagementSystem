package com.aqazadeh.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:15:22
 */

public record UpdateCategoryRequest(
        String name,
        String about,
        String slug,
        @JsonProperty("parent_id")
        Long parentId
) {
}
