package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.LoginRequest;
import com.aqazadeh.ecommerce.dto.request.ResetPasswordRequest;
import com.aqazadeh.ecommerce.dto.request.UserRegisterRequest;
import com.aqazadeh.ecommerce.dto.response.AuthDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.UserMapper;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.UserRepository;
import com.aqazadeh.ecommerce.service.AuthService;
import com.aqazadeh.ecommerce.service.TokenService;
import com.aqazadeh.ecommerce.service.UserService;
import com.aqazadeh.ecommerce.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.UUID;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 19:02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final UserService userService;
    private final MailUtil mailUtil;

    @Override
    public void register(UserRegisterRequest request) {
        if (userRepository.findByUsernameAndEmail(request.username(), request.email()).isPresent())
            throw new ApplicationException(ExceptionType.USERNAME_OR_EMAIL_EXISTS);
        User convert = userMapper.toUser(request);
        String password = passwordEncoder.encode(request.password());
        convert.setPassword(password);
        String key = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
        convert.setConfirmationToken(key);
        mailUtil.sendMessage(convert.getEmail(), "Eshop account activation", key);
        User user = userRepository.save(convert);

    }

    @Override
    @Transactional
    public AuthDto login(LoginRequest request) {
        String username = request.username();
        String password = passwordEncoder.encode(request.password());
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);

        if (!user.isEnabled()) {
            throw new ApplicationException(ExceptionType.USER_NOT_ACTIVATED);
        }

        if (user.isAccountNonLocked()) {
            throw new ApplicationException(ExceptionType.USER_IS_LOCKED);
        }

        String accessToken = tokenService.generateToken(user.getUsername(), 7200);
        String refreshToken = tokenService.generateToken(user.getUsername(), 604800);
        return new AuthDto(accessToken, refreshToken);

    }

    @Override
    public AuthDto refreshToken(String token) {
        String username = tokenService.getClaims(token).getSubject();
        UserDetails user = userService.loadUserByUsername(username);
        String accessToken = tokenService.generateToken(user.getUsername(), 7200);
        String refreshToken = tokenService.generateToken(user.getUsername(), 604800);
        return new AuthDto(accessToken, refreshToken);
    }

    @Override
    public void activate(String token) {
        User user = userService.findByConfirmationToken(token);
        user.setConfirmationToken(null);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String username) {
        User user = (User) userService.loadUserByUsername(username);
        String key = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
        user.setConfirmationToken(key);
        mailUtil.sendMessage(user.getEmail(), "Eshop password reset", key);
        userRepository.save(user);
    }

    @Override
    public void resetPasswordConfirm(ResetPasswordRequest request) {
        if (!request.password().equals(request.passwordConfirm())) {
            throw new ApplicationException(ExceptionType.PASSWORD_NOT_MATCH);
        }
        String password = passwordEncoder.encode(request.password());
        User user = userService.findByConfirmationToken(request.token());
        user.setPassword(password);
        user.setConfirmationToken(null);
        userRepository.save(user);
    }


}
