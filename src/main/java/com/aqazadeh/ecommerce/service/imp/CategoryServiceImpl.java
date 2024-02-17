package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.CategoryDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.CategoryMapper;
import com.aqazadeh.ecommerce.model.Category;
import com.aqazadeh.ecommerce.repository.CategoryRepository;
import com.aqazadeh.ecommerce.request.CreateCategoryRequest;
import com.aqazadeh.ecommerce.request.UpdateCategoryRequest;
import com.aqazadeh.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 15:32
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(CreateCategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        if (category.getSlug() == null) {
            String slug = category.getName() + new Random().nextInt(10);
            category.setSlug(slug);
        }
        if (request.parentId() != null) {
            Category parent = findById(request.parentId());
            category.setParent(parent);
        }
        categoryRepository.save(category);
    }

    @Override
    public void update(Long id, UpdateCategoryRequest request) {
        Category category = findById(id);
        Category newCategory = categoryMapper.toCategory(category, request);
        if (request.parentId() != null) {
            Category parent = findById(request.parentId());
            newCategory.setParent(parent);
        }
        categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public CategoryDto getById(Long id) {
        Category category = findById(id);

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    @Transactional
    public CategoryDto getBySlug(String slug) {
        Category category = findBySlug(slug);

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    @Transactional
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findByParentIsNull();
        return categories.stream().map(categoryMapper::toCategoryDto).toList();
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository
                .findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.CATEGORY_NOT_FOUND));
    }


    public Category findBySlug(String slug) {
        return categoryRepository
                .findBySlug(slug).
                orElseThrow(() -> new ApplicationException(ExceptionType.CATEGORY_NOT_FOUND));
    }
}
