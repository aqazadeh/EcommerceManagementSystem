package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.UserAddressDto;
import com.aqazadeh.ecommerce.dto.UserDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.model.UserAddress;
import com.aqazadeh.ecommerce.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.request.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:19:10
 */

public interface UserService extends UserDetailsService {
    UserDto getUserById(Long id);

    List<UserDto> getUsers(Integer page);

    void updateUser(UpdateUserRequest request, Long userId);

    void updateUserPassword(Long userId, UpdateUserPasswordRequest request);

    void deleteUser(Long id);

    UserAddressDto getUserAddress(Long userId, Long addressId);

    List<UserAddressDto> getUserAllAddresses(Long userId);

    void createUserAddress(Long userId, CreateUserAddressRequest request);

    void updateUserAddress(Long userId, Long addressId, UpdateUserAddressRequest request);

    void deleteUserAddress(Long userId, Long addressId);

    User findUserById(Long id);

    UserAddress findAddressById(Long addressId);
}
