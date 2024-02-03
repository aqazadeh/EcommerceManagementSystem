package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.UserAddressDto;
import com.aqazadeh.ecommerce.dto.UserDto;
import com.aqazadeh.ecommerce.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 2.02.2024
 * Time: 14:49
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page
    ){
        List<UserDto> dto = userService.getUsers(page);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        userService.updateUser(request, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/update-password/")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @RequestBody UpdateUserPasswordRequest request) {
        userService.updateUserPassword(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleterUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<List<UserAddressDto>> getUserAddresses(@PathVariable Long id) {
        List<UserAddressDto> userAllAddresses = userService.getUserAllAddresses(id);
        return ResponseEntity.ok(userAllAddresses);
    }

    @GetMapping("/{id}/address/{addressId}")
    public ResponseEntity<UserAddressDto> getUserAddress(@PathVariable Long id, @PathVariable Long addressId) {
        UserAddressDto userAddress = userService.getUserAddress(id, addressId);
        return ResponseEntity.ok(userAddress);
    }

    @PostMapping("/{id}/address/")
    public ResponseEntity<Void> createUserAddress(@PathVariable Long id, @RequestBody CreateUserAddressRequest request) {
        userService.createUserAddress(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/address/{addressId}")
    public ResponseEntity<Void> updateUserAddress(@PathVariable Long id, @PathVariable Long addressId,  @RequestBody UpdateUserAddressRequest request) {
        userService.updateUserAddress(id, addressId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/address/{addressId}")
    public ResponseEntity<Void> deleteUserAddress(@PathVariable Long id, @PathVariable Long addressId) {
        userService.deleteUserAddress(id, addressId);
        return ResponseEntity.ok().build();
    }


}
