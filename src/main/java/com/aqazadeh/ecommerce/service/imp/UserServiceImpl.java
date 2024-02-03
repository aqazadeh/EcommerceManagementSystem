package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.UserDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.UserMapper;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.UserRepository;
import com.aqazadeh.ecommerce.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 2.02.2024
 * Time: 15:20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${pagination.limit}")
    private Integer paginationLimit;

    @Override
    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getUsers(Integer page) {
        Pageable pageable = PageRequest.of(page, paginationLimit);
        List<User> all = userRepository.findAll(pageable).stream().toList();
        return all.stream().map(userMapper::toUserDto).toList();
    }

    @Override
    public void updateUser(UpdateUserRequest request, Long userId) {
        User user = findUserById(userId);
        User convertedUser = userMapper.toUser(user, request);
        userRepository.save(convertedUser);
    }

    @Override
    public void updateUserPassword(Long userId, UpdateUserPasswordRequest request) {
        User user = findUserById(userId);
        if(!user.getPassword().equals(request.oldPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);
        if (!request.newPassword().equals(request.newRepeatedPassword()))
            throw new ApplicationException(ExceptionType.PASSWORD_NOT_MATCH);
        user.setPassword(request.newPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    private User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }
}
