package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.*;
import com.aqazadeh.ecommerce.dto.response.ProductDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.ProductService;
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
    public ResponseEntity<Void> createProduct(
            @AuthenticationPrincipal User user,
            @RequestPart("media") MultipartFile[] media,
            @RequestPart("data") CreateProductRequest request
    ) {
        //TODO allow only image and video media type
        productService.create(user, media, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            @RequestBody UpdateProductRequest request
    ) {
        productService.update(user, productId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable String productId) {
        ProductDto productDto = productService.getById(productId);
        return ResponseEntity.ok(productDto);
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false) String categorySlug
    ) {
        List<ProductDto> productDtoList = productService.getAll(page, categorySlug);
        return ResponseEntity.ok(productDtoList);
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        productService.deleteProduct(user, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProduct(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestBody SearchProductRequest request
    ) {
        List<ProductDto> productDtoList = productService.search(page, request);
        return ResponseEntity.ok(productDtoList);
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PostMapping("/{productId}/discount")
    public ResponseEntity<Void> addDiscount(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            CreateDiscountRequest request
    ) {
        productService.addDiscount(user, productId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{productID}/discount/{discountId}")
    public ResponseEntity<Void> updateDiscount(
            @AuthenticationPrincipal User user,
            @PathVariable Long productID,
            @PathVariable Long discountId,
            @RequestBody UpdateDiscountRequest request
    ) {
        productService.updateDiscount(user, productID, discountId, request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{productId}/discount/")
    public ResponseEntity<Void> removeDiscount(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        productService.removeDiscount(user, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PostMapping("/{productId}/media")
    public ResponseEntity<Void> addMedia(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            @RequestPart MultipartFile file) {
        //TODO allow only image and video media type
        productService.addMedia(user, productId, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{productId}/media/{mediaId}")
    public ResponseEntity<Void> deleteMedia(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            @PathVariable Long mediaId
    ) {
        productService.removeMedia(user, productId, mediaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
