package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:45
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
}
