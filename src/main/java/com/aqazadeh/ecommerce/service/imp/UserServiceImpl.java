package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.dto.response.SessionDto;
import com.aqazadeh.ecommerce.dto.response.UserDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.SessionMapper;
import com.aqazadeh.ecommerce.mapper.UserMapper;
import com.aqazadeh.ecommerce.model.Session;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.UserRepository;
import com.aqazadeh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class UserServiceImpl implements UserService {
    private final SessionMapper sessionMapper;
    private final UserMapper userMapper;

    private final UserRepository repository;

    @Value("${pagination.limit}")
    private Integer paginationLimit;

    @Override
    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsers(Integer page) {
        Pageable pageable = PageRequest.of(page, paginationLimit);
        List<User> all = repository.findAll(pageable).stream().toList();
        return all.stream().map(userMapper::toDto).toList();
    }

    @Override
    public void updateUser(User user, UpdateUserRequest request) {
        User convertedUser = userMapper.toEntity(user, request);
        repository.save(convertedUser);
    }

    @Override
    public void updateUserPassword(User user, UpdateUserPasswordRequest request) {
        if (!user.getPassword().equals(request.oldPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);
        if (!request.newPassword().equals(request.newRepeatedPassword()))
            throw new ApplicationException(ExceptionType.PASSWORD_NOT_MATCH);
        user.setPassword(request.newPassword());
        repository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }


    @Override
    public User findUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    public User findByConfirmationToken(String token) {
        return repository.findByConfirmationToken(token).orElseThrow(() -> new ApplicationException(ExceptionType.INVALID_ACTIVATION_TOKEN));
    }

    @Override
    public List<SessionDto> getSessions(User user) {
        List<Session> sessions = user.getSessions();
        return sessions.stream().map(sessionMapper::toDto).toList();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }
}
