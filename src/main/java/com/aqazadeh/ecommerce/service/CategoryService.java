package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.CategoryDto;
import com.aqazadeh.ecommerce.model.Category;
import com.aqazadeh.ecommerce.request.CreateCategoryRequest;
import com.aqazadeh.ecommerce.request.UpdateCategoryRequest;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:6.02.2024
 * Time:15:27
 */

public interface CategoryService {
    void create(CreateCategoryRequest request);

    void update(Long id, UpdateCategoryRequest request);

    CategoryDto getById(Long id);

    CategoryDto getBySlug(String slug);

    List<CategoryDto> getAll();

    void delete(Long id);

    Category findBySlug(String slug);
}
