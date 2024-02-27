package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.dto.response.SessionDto;
import com.aqazadeh.ecommerce.dto.response.UserDto;
import com.aqazadeh.ecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

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

    void updateUser(User user, UpdateUserRequest request);

    void updateUserPassword(User user, UpdateUserPasswordRequest request);

    void deleteUser(User user);

    User findUserById(Long id);


    User findByConfirmationToken(String token);

    List<SessionDto> getSessions(User user);
}
