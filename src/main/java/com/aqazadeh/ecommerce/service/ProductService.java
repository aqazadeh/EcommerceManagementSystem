package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.response.ProductDto;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.dto.request.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:16:35
 */

public interface ProductService {

    void create(User user, MultipartFile[] media, CreateProductRequest request);

    void update(User user, Long id, UpdateProductRequest request);

    ProductDto getById(String id);

    List<ProductDto> getAll(Integer page, String categorySlug);

    List<ProductDto> getSellerAllProducts(Integer page, User user);

    void deleteProduct(User user, Long id);

    List<ProductDto> search(Integer page, SearchProductRequest request);

    Product findById(Long id);

    void addDiscount(User user, Long id, CreateDiscountRequest request);

    void addMedia(User user, Long id, MultipartFile file);

    void removeMedia(User user, Long productId, Long mediaId);

    void removeDiscount(User user, Long productId);

    void updateDiscount(User user, Long productID, UpdateDiscountRequest request);
}
