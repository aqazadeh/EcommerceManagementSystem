package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.response.FavoriteDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.FavoriteMapper;
import com.aqazadeh.ecommerce.model.Favorite;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.FavoriteRepository;
import com.aqazadeh.ecommerce.service.FavoriteService;
import com.aqazadeh.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 24.02.2024
 * Time: 21:45
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository repository;
    private final ProductService productService;
    private final FavoriteMapper favoriteMapper;

    @Value("${pagination.limit}")
    private Integer pageLimit;

    @Override
    public void addToFavorites(User user, Long productId) {
        Product product = productService.findById(productId);
        Favorite favorite = Favorite.builder()
                .user(user)
                .product(product)
                .build();

        repository.save(favorite);
    }


    @Override
    public void removeToFavorites(User user, Long productId) {
        Product product = productService.findById(productId);
        Favorite favorite = findByUserAndProduct(product, user);
        repository.delete(favorite);
    }

    @Override
    public List<FavoriteDto> getAllFavorites(User user, Integer page) {
        Pageable pageable = PageRequest.of(page, pageLimit);
        List<Favorite> favorites = repository.findByUser(user, pageable).stream().toList();
        return favorites.stream().map(favoriteMapper::toDto).toList();
    }

    @Override
    public Favorite findByUserAndProduct(Product product, User user) {
        return repository.findByProductAndUser(product, user)
                .orElseThrow(() -> new ApplicationException(ExceptionType.FAVORITE_NOT_FOUND));
    }

    @Override
    public Favorite findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.FAVORITE_NOT_FOUND));
    }
}
