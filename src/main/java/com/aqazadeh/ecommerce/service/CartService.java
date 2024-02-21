package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.CreateCartRequest;
import com.aqazadeh.ecommerce.dto.response.CartDto;
import com.aqazadeh.ecommerce.model.Cart;
import com.aqazadeh.ecommerce.model.User;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:19.02.2024
 * Time:01:44
 */

public interface CartService {

    List<CartDto> getUserCart(User user);

    void removeFromCart(User user, Long itemId);

    void clearCart(User user);

    Cart findById(Long id);

    void addToCart(User user, CreateCartRequest request);
}
