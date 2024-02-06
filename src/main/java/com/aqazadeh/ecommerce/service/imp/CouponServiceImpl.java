package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.CouponDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.CouponMapper;
import com.aqazadeh.ecommerce.model.Coupon;
import com.aqazadeh.ecommerce.model.enums.CouponType;
import com.aqazadeh.ecommerce.repository.CouponRepository;
import com.aqazadeh.ecommerce.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.request.UpdateCouponRequest;
import com.aqazadeh.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 17:44
 */
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    @Value("${pagination.limit}")
    private Integer limit;
    @Override
    public void create(CreateCouponRequest request) {
        if (couponRepository.findByCode(request.code()).isPresent()) {
            throw new ApplicationException(ExceptionType.COUPON_CODE_ALREADY_EXISTS);
        }

        if(request.couponType()== CouponType.PERCENT) {
            if (request.percentCount() == null)
                throw new ApplicationException(ExceptionType.COUPON_CREATE_ERROR);
        }

        if(request.couponType()== CouponType.CASH) {
            if (request.cashCount() == null)
                throw new ApplicationException(ExceptionType.COUPON_CREATE_ERROR);
        }
        
        Coupon coupon = couponMapper.toCoupon(request);
        couponRepository.save(coupon);
    }

    @Override
    public void update(Long id, UpdateCouponRequest request) {

    }

    @Override
    public List<CouponDto> getAll(Integer page) {

        Pageable pageable = PageRequest.of(page, limit);
        List<Coupon> coupons = couponRepository.findAll(pageable).toList();
        List<CouponDto> couponDtos = coupons.stream().map(couponMapper::toCouponDto).toList();
        return couponDtos;
    }

    @Override
    public CouponDto getById(Long id) {
        Coupon coupon = findById(id);
        CouponDto couponDto = couponMapper.toCouponDto(coupon);
        return couponDto;
    }

    @Override
    public void delete(Long id, UpdateCouponRequest request) {
        Coupon coupon = findById(id);
        couponRepository.delete(coupon);
    }

    private Coupon findById(Long id) {
        return couponRepository.findById(id).orElseThrow(() -> new ApplicationException(ExceptionType.COUPON_NOT_FOUND));
    }

    @Override
    public Coupon getByCode(String code) {
        return couponRepository.findByCode(code).orElseThrow(() -> new ApplicationException(ExceptionType.COUPON_NOT_FOUND));
    }


}
