package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateCategoryRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateCategoryRequest;
import com.aqazadeh.ecommerce.dto.response.CategoryDto;
import com.aqazadeh.ecommerce.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:15:33
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);

    Category toEntity(@MappingTarget Category category, UpdateCategoryRequest request);

}
