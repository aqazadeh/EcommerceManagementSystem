package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Comment;
import com.aqazadeh.ecommerce.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 18:38
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductAndParentIsNull(Product product, Pageable pageable);
}
