package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Discount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:44
 */

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query("SELECT d FROM Discount d WHERE d.expiredTime > :time")
    List<Discount> findAll(LocalDateTime time, Pageable pageable);
}
