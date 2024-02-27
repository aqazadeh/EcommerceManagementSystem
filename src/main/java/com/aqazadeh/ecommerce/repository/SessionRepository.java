package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 26.02.2024
 * Time: 23:31
 */

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByRefreshToken(String token);
}
