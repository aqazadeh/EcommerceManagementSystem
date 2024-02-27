package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.CreateRatingRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateRatingRequest;
import com.aqazadeh.ecommerce.dto.response.RatingDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 01:02
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/rating")
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Void> addRating(
            @AuthenticationPrincipal User user,
            @RequestPart("data") CreateRatingRequest request,
            @RequestPart("media") MultipartFile[] files) {
        ratingService.addRating(user, files, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRating(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            @RequestBody UpdateRatingRequest request) {
        ratingService.updateRating(user, id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<RatingDto>> getProductRatings(
            @PathVariable Long productId,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        List<RatingDto> ratingDtoList = ratingService.getProductRatings(productId, page);
        return ResponseEntity.ok(ratingDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDto> getById(@PathVariable Long id) {
        RatingDto ratingDto = ratingService.getById(id);
        return ResponseEntity.ok(ratingDto);
    }
}
