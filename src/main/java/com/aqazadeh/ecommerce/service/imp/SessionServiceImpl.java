package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.response.AuthDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.model.Session;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.SessionRepository;
import com.aqazadeh.ecommerce.service.SessionService;
import com.aqazadeh.ecommerce.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 26.02.2024
 * Time: 23:30
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionServiceImpl implements SessionService {
    private final SessionRepository repository;
    private final TokenService tokenService;

    @Override
    public AuthDto create(User user, HttpServletRequest request) {

        String accessToken = tokenService.generateToken(user.getUsername());
        Session session = Session.builder()
                .ip(request.getRemoteAddr())
                .accessToken(accessToken)
                .refreshToken(UUID.randomUUID().toString())
                .device(request.getHeader("User-Agent"))
                .user(user)
                .build();

        Session save = repository.save(session);
        return new AuthDto(save.getAccessToken(), save.getRefreshToken());
    }

    @Override
    public AuthDto refreshToken(String token, HttpServletRequest request) {
        Session session = repository.findByRefreshToken(token).orElseThrow(() -> new ApplicationException(ExceptionType.INVALID_REFRESH_TOKEN));
        User user = session.getUser();
        AuthDto authDto = create(user, request);
        repository.delete(session);
        return authDto;
    }
}
