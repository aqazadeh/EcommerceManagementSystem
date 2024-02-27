package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateDiscountRequest;
import com.aqazadeh.ecommerce.mapper.DiscountMapper;
import com.aqazadeh.ecommerce.model.Discount;
import com.aqazadeh.ecommerce.repository.DiscountRepository;
import com.aqazadeh.ecommerce.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 9.02.2024
 * Time: 15:58
 */
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository repository;
    private final DiscountMapper discountMapper;

    @Override
    public Discount create(CreateDiscountRequest request) {
        Discount discount = discountMapper.toEntity(request);
        return repository.save(discount);
    }

    @Override
    public void update(Long id, UpdateDiscountRequest request) {
        Discount discount = findById(id);
        Discount newDiscount = discountMapper.toEntity(discount, request);
        repository.save(newDiscount);
    }

    @Override
    public void delete(Long id) {
        Discount discount = findById(id);
        repository.delete(discount);
    }

    @Override
    public Discount findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
