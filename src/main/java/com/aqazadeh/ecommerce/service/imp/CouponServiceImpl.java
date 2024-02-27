package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.CreateCouponRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateCouponRequest;
import com.aqazadeh.ecommerce.dto.response.CouponDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.CouponMapper;
import com.aqazadeh.ecommerce.model.Coupon;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.model.enums.CouponType;
import com.aqazadeh.ecommerce.repository.CouponRepository;
import com.aqazadeh.ecommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 17:44
 */
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;
    private final CouponMapper couponMapper;
    @Value("${pagination.limit}")
    private Integer limit;

    @Override
    public void create(User user, CreateCouponRequest request) {
        if (repository.findByCode(request.code()).isPresent()) {
            throw new ApplicationException(ExceptionType.COUPON_CODE_ALREADY_EXISTS);
        }

        if (request.couponType() == CouponType.PERCENT) {
            if (request.percentCount() == null)
                throw new ApplicationException(ExceptionType.COUPON_CREATE_ERROR);
        }

        if (request.couponType() == CouponType.CASH) {
            if (request.cashCount() == null)
                throw new ApplicationException(ExceptionType.COUPON_CREATE_ERROR);
        }

        Coupon coupon = couponMapper.toEntity(request);
        coupon.setCreator(user);
        repository.save(coupon);
    }

    @Override
    public void update(User user, Long id, UpdateCouponRequest request) {
        Coupon coupon = findById(id);

        if (!coupon.getCreator().equals(user))
            throw new ApplicationException(ExceptionType.COUPON_NOT_FOUND);

        if (request.couponType() == CouponType.PERCENT) {
            if (request.percentCount() == null)
                throw new ApplicationException(ExceptionType.COUPON_CREATE_ERROR);
        }

        if (request.couponType() == CouponType.CASH) {
            if (request.cashCount() == null)
                throw new ApplicationException(ExceptionType.COUPON_CREATE_ERROR);
        }
        Coupon updatedCoupon = couponMapper.toEntity(coupon, request);
        repository.save(updatedCoupon);
    }

    @Override
    public CouponDto getById(Long id) {
        Coupon coupon = findById(id);
        return couponMapper.toDto(coupon);
    }

    @Override
    public CouponDto getByCode(String code) {
        return couponMapper.toDto(findByCode(code));
    }

    @Override
    public void delete(User user, Long id) {
        Coupon coupon = findById(id);

        if (!coupon.getCreator().equals(user))
            throw new ApplicationException(ExceptionType.COUPON_NOT_FOUND);

        repository.delete(coupon);
    }

    @Override
    public Coupon findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ApplicationException(ExceptionType.COUPON_NOT_FOUND));
    }

    @Override
    public Coupon findByCode(String code) {
        return repository.findByCode(code).orElseThrow(() -> new ApplicationException(ExceptionType.COUPON_NOT_FOUND));
    }


}
