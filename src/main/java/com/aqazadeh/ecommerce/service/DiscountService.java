package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.model.Discount;
import com.aqazadeh.ecommerce.dto.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateDiscountRequest;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:9.02.2024
 * Time:15:51
 */

public interface DiscountService {
    Discount create(CreateDiscountRequest request);

    void update(Long id, UpdateDiscountRequest request);

    void delete(Long id);

    Discount findById(Long id);
}
