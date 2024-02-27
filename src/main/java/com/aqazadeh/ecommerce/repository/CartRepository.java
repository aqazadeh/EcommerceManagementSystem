package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Cart;
import com.aqazadeh.ecommerce.model.ProductVariant;
import com.aqazadeh.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:41
 */

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByVariantAndUser(ProductVariant variant, User user);
}
