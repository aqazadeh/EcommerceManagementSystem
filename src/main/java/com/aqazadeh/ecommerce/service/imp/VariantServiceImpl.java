package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.model.ProductVariant;
import com.aqazadeh.ecommerce.repository.ProductVariantRepository;
import com.aqazadeh.ecommerce.service.VariantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 25.02.2024
 * Time: 02:09
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VariantServiceImpl implements VariantService {
    private final ProductVariantRepository repository;

    @Override
    public ProductVariant findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.VARIANT_NOT_FOUND));
    }
}
