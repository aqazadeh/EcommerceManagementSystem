package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.UserDto;
import com.aqazadeh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UserDto> getUserById(@RequestBody Long id) {
        return null;
    }
}
