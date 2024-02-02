package com.aqazadeh.ecommerce.exception;

import lombok.Getter;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:19:16
 */
@Getter
public enum ExceptionType {
    USER_NOT_FOUND("user not found"),
    USER_INVALID_PASSWORD("invalid password"),
    USERNAME_OR_EMAIL_EXISTS("username or email already exists");

    private String message;
    ExceptionType(String message) {
        this.message = message;
    }
}
