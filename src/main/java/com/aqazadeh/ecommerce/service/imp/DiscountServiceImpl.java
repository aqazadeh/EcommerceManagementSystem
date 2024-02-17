package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.DiscountDto;
import com.aqazadeh.ecommerce.mapper.DiscountMapper;
import com.aqazadeh.ecommerce.model.Discount;
import com.aqazadeh.ecommerce.repository.DiscountRepository;
import com.aqazadeh.ecommerce.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.request.UpdateDiscountRequest;
import com.aqazadeh.ecommerce.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 9.02.2024
 * Time: 15:58
 */
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public Discount create(CreateDiscountRequest request) {
        Discount discount = discountMapper.toDiscount(request);
        return discountRepository.save(discount);
    }

    @Override
    public void update(Long id, UpdateDiscountRequest request) {
        Discount discount = findById(id);
        Discount newDiscount = discountMapper.toDiscount(discount, request);
        discountRepository.save(newDiscount);
    }

    @Override
    public void delete(Long id) {
        Discount discount = findById(id);
        discountRepository.delete(discount);
    }

    @Override
    public Discount findById(Long id) {
        return discountRepository.findById(id).orElseThrow();
    }
}
