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
    USERNAME_OR_EMAIL_EXISTS("username or email already exists"),
    PASSWORD_NOT_MATCH("PASSWORD NOT MATCH"),
    TOKEN_EXPIRED("TOken expired"),

    ADDRESS_NOT_FOUND("Address not found"),
    CATEGORY_NOT_FOUND("category not found"),
    COUPON_NOT_FOUND("coupon not found"),
    COUPON_CODE_ALREADY_EXISTS("COUPON_CODE_ALREADY_EXISTS"), COUPON_CREATE_ERROR("invalid created data"),
    PRODUCT_NOT_FOUND("product not found");

    private final String info;

    ExceptionType(String message) {
        this.info = message;
    }
}
