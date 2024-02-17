package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.ProductDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.ProductMapper;
import com.aqazadeh.ecommerce.model.*;
import com.aqazadeh.ecommerce.repository.ProductRepository;
import com.aqazadeh.ecommerce.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.request.CreateProductRequest;
import com.aqazadeh.ecommerce.request.SearchProductRequest;
import com.aqazadeh.ecommerce.request.UpdateProductRequest;
import com.aqazadeh.ecommerce.service.CategoryService;
import com.aqazadeh.ecommerce.service.DiscountService;
import com.aqazadeh.ecommerce.service.MediaService;
import com.aqazadeh.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.02.2024
 * Time: 18:09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final MediaService mediaService;
    private final DiscountService discountService;

    @Value("${pagination.limit}")
    private Integer pageLimit;

    @Override
    public void create(User user, MultipartFile[] media, CreateProductRequest request) {
        Category category = categoryService.findById(request.categoryId());

        Product product = productMapper.toEntity(request);
        product.setUser(user);
        product.setCategory(category);

        List<Media> mediaList = Arrays.stream(media)
                .map(file -> mediaService.upload(file, product))
                .toList();

        List<Inventory> inventoryList = request.inventory().stream()
                .map(productMapper::toEntity)
                .peek(inventory -> inventory.setProduct(product))
                .toList();
        product.setInventory(inventoryList);
        product.setMedia(mediaList);
        productRepository.save(product);

    }

    @Override
    public void update(User user, Long id, UpdateProductRequest request) {
        Product product = findById(id);
        if (product.getUser().equals(user)) {
            if (request.categoryId() != null) {
                Category category = categoryService.findById(request.categoryId());
                product.setCategory(category);
            }
            Product updatedProduct = productMapper.toEntity(product, request);
            productRepository.save(updatedProduct);
        } else {
            //TODO add new exception type
            throw new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public ProductDto getById(String id) {
        Product product;
        try {
            product = findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            product = findBySlug(id);
        }
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAll(Integer page, String categorySlug) {
        Pageable pageable = PageRequest.of(page, pageLimit);
        if (categorySlug != null) {
            return categoryService.findBySlug(categorySlug).getProducts().stream().map(productMapper::toDto).toList();
        }
        return productRepository.findAll(pageable).stream().map(productMapper::toDto).toList();
    }

    @Override
    public void delete(User user, Long id) {
        //TODO Remove media from cloudinary
        Product product = findById(id);
        if (product.getUser().equals(user)) {
            productRepository.delete(product);
        } else {
            //TODO add new exception type
            throw new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<ProductDto> search(Integer page, SearchProductRequest request) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product findBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND));
    }

    @Override
    public void addDiscount(User user, Long id, CreateDiscountRequest request) {
        Discount discount = discountService.create(request);
        Product product = findById(id);
        product.setDiscount(discount);
        productRepository.save(product);
    }

    @Override
    public void addMedia(User user, Long id, MultipartFile file) {
        Product product = findById(id);
        if (product.getUser().equals(user)) {
            Media media = mediaService.upload(file, product);
            media.setProduct(product);
            List<Media> mediaList = product.getMedia();
            mediaList.add(media);
            product.setMedia(mediaList);
            productRepository.save(product);
        } else {
            //TODO add new exception type
            throw new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public void removeMedia(User user, Long productId, Long mediaId) {

    }

    @Override
    public void removeDiscount(User user, Long productId, Long discountId) {

    }

}
