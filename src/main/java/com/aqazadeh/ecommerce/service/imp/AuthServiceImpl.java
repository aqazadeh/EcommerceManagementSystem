package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.AuthDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.UserMapper;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.UserRepository;
import com.aqazadeh.ecommerce.request.LoginRequest;
import com.aqazadeh.ecommerce.request.UserRegisterRequest;
import com.aqazadeh.ecommerce.service.AuthService;
import com.aqazadeh.ecommerce.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public void register(UserRegisterRequest request) {
        if (userRepository.findByUsernameAndEmail(request.username(), request.email()).isPresent())
            throw new ApplicationException(ExceptionType.USERNAME_OR_EMAIL_EXISTS);
        User convert = userMapper.toUser(request);
        String password = passwordEncoder.encode(request.password());
        convert.setPassword(password);
        userRepository.save(convert);
    }

    @Override
    public AuthDto login(LoginRequest request) {
        String username = request.username();
        String password = request.password();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);

        String accessToken = tokenService.generateToken(user.getUsername(), 7200);
        String refreshToken = tokenService.generateToken(user.getUsername(), 604800);
        return new AuthDto(accessToken, refreshToken);

    }

    @Override
    public AuthDto refreshToken(String token) {
        return new AuthDto("access_token" + UUID.randomUUID(), "refresh_token" + UUID.randomUUID());
    }


}
