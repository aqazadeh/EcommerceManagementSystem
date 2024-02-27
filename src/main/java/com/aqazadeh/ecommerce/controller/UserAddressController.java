package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.response.UserAddressDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.UserAddressService;
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
 * Date: 27.02.2024
 * Time: 00:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/address")
@Slf4j
public class UserAddressController {
    private final UserAddressService addressService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserAddressDto>> getUserAddresses(
            @AuthenticationPrincipal User user
    ) {
        List<UserAddressDto> userAllAddresses = addressService.getUserAllAddresses(user);
        return ResponseEntity.ok(userAllAddresses);
    }

    @GetMapping("/{addressId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserAddressDto> getUserAddress(
            @AuthenticationPrincipal User user,
            @PathVariable Long addressId
    ) {
        UserAddressDto userAddress = addressService.getUserAddress(user, addressId);
        return ResponseEntity.ok(userAddress);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> createUserAddress(
            @AuthenticationPrincipal User user,
            @RequestBody CreateUserAddressRequest request
    ) {
        addressService.createUserAddress(user, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{addressId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateUserAddress(
            @AuthenticationPrincipal User user,
            @PathVariable Long addressId,
            @RequestBody UpdateUserAddressRequest request
    ) {
        addressService.updateUserAddress(user, addressId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{addressId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteUserAddress(
            @AuthenticationPrincipal User user,
            @PathVariable Long addressId
    ) {
        addressService.deleteUserAddress(user, addressId);
        return ResponseEntity.ok().build();
    }
}
