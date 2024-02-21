package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.CategoryDto;
import com.aqazadeh.ecommerce.model.Category;
import com.aqazadeh.ecommerce.dto.request.CreateCategoryRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateCategoryRequest;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:15:33
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);
    Category toCategory(CreateCategoryRequest request);
    Category toCategory(@MappingTarget Category category, UpdateCategoryRequest request);

}
