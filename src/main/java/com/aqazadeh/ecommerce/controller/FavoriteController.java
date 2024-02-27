package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.response.FavoriteDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 25.02.2024
 * Time: 03:01
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/favorite")
public class FavoriteController {
    private final FavoriteService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{productId}")
    public ResponseEntity<Void> addToFavorite(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        service.addToFavorites(user, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> removeTo(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        service.removeToFavorites(user, productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<FavoriteDto>> getUserFavorites(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        List<FavoriteDto> favorites = service.getAllFavorites(user, page);
        return ResponseEntity.ok(favorites);
    }

}
