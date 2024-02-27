package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Favorite;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 24.02.2024
 * Time: 21:46
 */

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByProductAndUser(Product product, User user);

    Page<Favorite> findByUser(User user, Pageable pageable);
}
