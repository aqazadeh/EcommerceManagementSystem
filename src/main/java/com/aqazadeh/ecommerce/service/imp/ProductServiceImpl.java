package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.*;
import com.aqazadeh.ecommerce.dto.response.ProductDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.DiscountMapper;
import com.aqazadeh.ecommerce.mapper.ProductMapper;
import com.aqazadeh.ecommerce.model.*;
import com.aqazadeh.ecommerce.repository.ProductRepository;
import com.aqazadeh.ecommerce.service.CategoryService;
import com.aqazadeh.ecommerce.service.DiscountService;
import com.aqazadeh.ecommerce.service.MediaService;
import com.aqazadeh.ecommerce.service.ProductService;
import com.aqazadeh.ecommerce.util.Slugify;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
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
    private final Slugify slugify;
    private final Cloudinary cloudinary;
    private final DiscountMapper discountMapper;

    @Value("${pagination.limit}")
    private Integer pageLimit;

    @Override
    public void create(User user, MultipartFile[] media, CreateProductRequest request) {

        Category category = categoryService.findById(request.categoryId());

        Product product = productMapper.toEntity(request);
        product.setUser(user);
        product.setCategory(category);

        List<ProductVariant> productVariantList = product.getVariants().stream()
                .peek(variant -> variant.setProduct(product))
                .toList();

        product.setVariants(productVariantList);
        Product saved = productRepository.save(product);

        Arrays.stream(media)
                .forEach(file -> mediaService.upload(file, saved));


    }

    @Override
    public void update(User user, Long id, UpdateProductRequest request) {
        Product product = findSellerProductById(id, user);
        if (request.categoryId() != null) {
            Category category = categoryService.findById(request.categoryId());
            product.setCategory(category);
        }
        Product updatedProduct = productMapper.toEntity(product, request);
        productRepository.save(updatedProduct);

    }

    @Override
    public ProductDto getById(String id) {
        Long decodeId = slugify.decode(id);
        Product product = findById(decodeId);
        product.getMedia().forEach(media -> media.setUrl(cloudinary.url().secure(true).generate(media.getUrl())));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAll(Integer page, String categorySlug) {
        Pageable pageable = PageRequest.of(page, pageLimit);
        if (categorySlug != null) {
            Category category = categoryService.findBySlug(categorySlug);

            return productRepository.findByCategory(category, pageable).stream().map(productMapper::toDto).toList();
        }
        return productRepository.findAll(pageable).stream().map(productMapper::toDto).toList();
    }

    @Override
    public List<ProductDto> getSellerAllProducts(Integer page, User user) {
        Pageable pageable = PageRequest.of(page, pageLimit);
        return productRepository.findAllByUser(user, pageable).stream().map(productMapper::toDto).toList();
    }

    @Override
    public void deleteProduct(User user, Long id) {
        Product product = findSellerProductById(id, user);
        product.getMedia().forEach(media -> mediaService.delete(media.getId()));
        productRepository.delete(product);
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
    public void addDiscount(User user, Long id, CreateDiscountRequest request) {
        Discount discount = discountService.create(request);
        Product product = findById(id);
        product.setDiscount(discount);
        productRepository.save(product);
    }

    @Override
    public void addMedia(User user, Long id, MultipartFile file) {
        Product product = findSellerProductById(id, user);
        Media media = mediaService.upload(file, product);
        media.setProduct(product);
        List<Media> mediaList = product.getMedia();
        mediaList.add(media);
        product.setMedia(mediaList);
        productRepository.save(product);
    }

    @Override
    public void removeMedia(User user, Long productId, Long mediaId) {
        Product product = findSellerProductById(productId, user);
        Media media = product.getMedia().stream()
                .filter(m -> m.getId().equals(mediaId))
                .findFirst().orElseThrow(() -> new ApplicationException(ExceptionType.MEDIA_NOT_FOUND));
        mediaService.delete(media.getId());

    }

    @Override
    public void removeDiscount(User user, Long productId) {
        Product product = findSellerProductById(productId, user);
        product.setDiscount(null);
        productRepository.save(product);
    }

    @Override
    public void updateDiscount(User user, Long productID, UpdateDiscountRequest request) {
        Product product = findSellerProductById(productID, user);
        discountMapper.toDiscount(product.getDiscount(), request);
        productRepository.save(product);
    }

    private Product findSellerProductById(Long id, User user) {
        Product product = findById(id);
        if (product.getUser().equals(user)) {
            return product;
        }
        throw new ApplicationException(ExceptionType.PRODUCT_NOT_FOUND);
    }

}
