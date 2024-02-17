package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.ProductDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.request.CreateProductRequest;
import com.aqazadeh.ecommerce.request.SearchProductRequest;
import com.aqazadeh.ecommerce.request.UpdateProductRequest;
import com.aqazadeh.ecommerce.service.ProductService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.02.2024
 * Time: 16:35
 */
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> createProduct(@AuthenticationPrincipal User user,
                                              @RequestPart("media") MultipartFile[] media,
                                              @RequestPart("data") CreateProductRequest request
    ) {
        //TODO allow only image and video media type
        productService.create(user, media, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PostMapping("/{id}/discount")
    public ResponseEntity<Void> addDiscount(@AuthenticationPrincipal User user, @PathVariable Long id, CreateDiscountRequest request) {
        productService.addDiscount(user, id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{productId}/discount/{discountId}")
    public ResponseEntity<Void> removeDiscount(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            @PathVariable Long discountId
    ) {
        productService.removeDiscount(user, productId, discountId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PostMapping("/{id}/media")
    public ResponseEntity<Void> addMedia(@AuthenticationPrincipal User user, @PathVariable Long id, MultipartFile file) {
        //TODO allow only image and video media type
        productService.addMedia(user, id, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{productId}/media/{mediaId}")
    public ResponseEntity<Void> deleteMedia(@AuthenticationPrincipal User user, @PathVariable Long productId, @PathVariable Long mediaId) {
        productService.removeMedia(user, productId, mediaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestBody UpdateProductRequest request) {
        productService.update(user, id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable String id) {
        ProductDto productDto = productService.getById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false) String categorySlug
    ) {
        List<ProductDto> productDtoList = productService.getAll(page, categorySlug);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@AuthenticationPrincipal User user, @PathVariable Long id) {
        productService.delete(user, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestParam Integer page, @RequestBody SearchProductRequest request) {
        List<ProductDto> productDtoList = productService.search(page, request);
        return ResponseEntity.ok(productDtoList);
    }
}
