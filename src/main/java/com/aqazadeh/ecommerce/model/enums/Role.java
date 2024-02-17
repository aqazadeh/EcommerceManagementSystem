package com.aqazadeh.ecommerce.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:9.02.2024
 * Time:13:31
 */

public enum Role implements GrantedAuthority {
    ADMIN,
    SELLER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
