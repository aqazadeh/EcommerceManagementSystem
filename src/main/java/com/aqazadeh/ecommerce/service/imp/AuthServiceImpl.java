package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.AuthDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.UserRepository;
import com.aqazadeh.ecommerce.request.LoginRequest;
import com.aqazadeh.ecommerce.request.UserRegisterRequest;
import com.aqazadeh.ecommerce.service.AuthService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 19:02
 */
@Service
@RequiredArgsConstructor
@Aspect
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
//    private final UserService userService;
    @Override
    public void register(UserRegisterRequest request) {
        if (userRepository.findByUsernameAndEmail(request.username(), request.email()).isPresent())
            throw new ApplicationException(ExceptionType.USERNAME_OR_EMAIL_EXISTS);
        User convert = UserRegisterRequest.convert(request);
        userRepository.save(convert);
    }

    @Override
    public AuthDto login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
        if(!user.getPassword().equals(request.password()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);

        return new AuthDto("access_token", "refresh_token");

    }

    @Override
    public AuthDto refreshToken(String token) {
        return new AuthDto("access_token" + UUID.randomUUID(), "refresh_token" + UUID.randomUUID());
    }
}
