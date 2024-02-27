package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.response.FavoriteDto;
import com.aqazadeh.ecommerce.model.Favorite;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.User;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 24.02.2024
 * Time: 21:42
 */

public interface FavoriteService {

    void addToFavorites(User user, Long productId);

    void removeToFavorites(User user, Long productId);

    List<FavoriteDto> getAllFavorites(User user, Integer page);

    Favorite findByUserAndProduct(Product product, User user);

    Favorite findById(Long id);
}
