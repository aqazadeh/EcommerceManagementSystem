package com.aqazadeh.ecommerce.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:15:18
 */

public record CreateCategoryRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String about,
        String slug,
        @JsonProperty("parent_id")
        Long parentId
) {
}
