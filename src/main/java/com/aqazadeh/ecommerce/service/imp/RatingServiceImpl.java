package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.CreateRatingRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateRatingRequest;
import com.aqazadeh.ecommerce.dto.response.RatingDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.RatingMapper;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.Rating;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.RatingRepository;
import com.aqazadeh.ecommerce.service.MediaService;
import com.aqazadeh.ecommerce.service.ProductService;
import com.aqazadeh.ecommerce.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 01:12
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    private final ProductService productService;
    private final MediaService mediaService;

    private final RatingMapper ratingMapper;

    @Value("${pagination.limit}")
    private Integer pageLimit;

    @Override
    @Transactional
    public void addRating(User user, MultipartFile[] files, CreateRatingRequest request) {
        Product product = productService.findById(request.productId());
        Rating rating = ratingMapper.toEntity(request);
        rating.setProduct(product);
        ratingRepository.save(rating);
        Arrays.stream(files).forEach(file -> mediaService.upload(file, rating));
    }

    @Override
    public void updateRating(User user, Long id, UpdateRatingRequest request) {
        Rating rating = findById(id);
        if (!rating.getUser().equals(user))
            throw new ApplicationException(ExceptionType.RATING_NOT_FOUND);

        Rating newRating = ratingMapper.toEntity(rating, request);
        ratingRepository.save(newRating);
    }

    @Override
    public List<RatingDto> getProductRatings(Long productId, Integer page) {
        Pageable pageable = PageRequest.of(page, pageLimit);
        Product product = productService.findById(productId);
        List<Rating> ratings = ratingRepository.findByProduct(product, pageable);
        return ratings.stream().map(ratingMapper::toDto).toList();
    }

    @Override
    public RatingDto getById(Long id) {
        Rating rating = findById(id);
        return ratingMapper.toDto(rating);
    }

    @Override
    public Rating findById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.RATING_NOT_FOUND));
    }
}
