package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.CategoryDto;
import com.aqazadeh.ecommerce.request.CreateCategoryRequest;
import com.aqazadeh.ecommerce.request.UpdateCategoryRequest;
import com.aqazadeh.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 15:07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest request) {
        categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest request) {
        categoryService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = categoryService.getById(id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/slug/{slug}")
    public ResponseEntity<CategoryDto> getCategoryBySlug(@PathVariable String slug) {
        CategoryDto categoryDto = categoryService.getBySlug(slug);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        return ResponseEntity.ok(categoryDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
