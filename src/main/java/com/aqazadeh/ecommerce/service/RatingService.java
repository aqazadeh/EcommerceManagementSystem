package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.CreateRatingRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateRatingRequest;
import com.aqazadeh.ecommerce.dto.response.RatingDto;
import com.aqazadeh.ecommerce.model.Rating;
import com.aqazadeh.ecommerce.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 01:02
 */

public interface RatingService {
    void addRating(User user, MultipartFile[] files, CreateRatingRequest request);

    void updateRating(User user, Long id, UpdateRatingRequest request);

    List<RatingDto> getProductRatings(Long productId, Integer page);

    RatingDto getById(Long id);

    Rating findById(Long id);
}
