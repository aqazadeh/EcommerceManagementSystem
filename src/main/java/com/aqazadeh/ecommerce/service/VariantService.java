package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.model.ProductVariant;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 25.02.2024
 * Time: 02:06
 */

public interface VariantService {
    ProductVariant findById(Long id);
}
