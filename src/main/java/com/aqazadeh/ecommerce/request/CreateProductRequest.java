package com.aqazadeh.ecommerce.request;

import com.aqazadeh.ecommerce.dto.CategoryDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:17:21
 */

public record CreateProductRequest(
        @NotEmpty String name,
        @NotEmpty String description,
        @NotNull Long categoryId,
        List<CreateInventoryRequest> inventory
) {
}
