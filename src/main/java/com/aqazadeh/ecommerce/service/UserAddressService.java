package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.response.UserAddressDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.model.UserAddress;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 26.02.2024
 * Time: 23:47
 */

public interface UserAddressService {
    void createUserAddress(User user, CreateUserAddressRequest request);

    void updateUserAddress(User user, Long addressId, UpdateUserAddressRequest request);

    void deleteUserAddress(User user, Long addressId);

    UserAddress findAddressById(Long addressId);

    List<UserAddressDto> getUserAllAddresses(User user);

    UserAddressDto getUserAddress(User user, Long addressId);
}
