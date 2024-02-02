package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:47
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
}
