package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:41
 */

public interface CartRepository extends JpaRepository<Cart, Long> {
}
