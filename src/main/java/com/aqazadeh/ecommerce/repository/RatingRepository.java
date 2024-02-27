package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 01:13
 */

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByProduct(Product product, Pageable pageable);
}
