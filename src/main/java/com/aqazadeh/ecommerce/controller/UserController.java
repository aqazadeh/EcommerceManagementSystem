package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.dto.response.SessionDto;
import com.aqazadeh.ecommerce.dto.response.UserDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        List<UserDto> dto = userService.getUsers(page);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateUser(
            @AuthenticationPrincipal User user,
            @RequestBody UpdateUserRequest request) {
        userService.updateUser(user, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-password/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateUserPassword(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UpdateUserPasswordRequest request
    ) {
        userService.updateUserPassword(user, request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleterUser(
            @AuthenticationPrincipal User user
    ) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/sessions")
    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<List<SessionDto>> getUserSessions(@AuthenticationPrincipal User user) {
        List<SessionDto> sessionDtoList = userService.getSessions(user);
        return ResponseEntity.ok(sessionDtoList);
    }
}
