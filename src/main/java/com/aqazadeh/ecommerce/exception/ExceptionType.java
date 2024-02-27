package com.aqazadeh.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:19:16
 */
@Getter
public enum ExceptionType {
    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    USER_INVALID_PASSWORD("invalid password", HttpStatus.BAD_REQUEST),
    USERNAME_OR_EMAIL_EXISTS("username or email already exists", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH("PASSWORD NOT MATCH", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED("TOken expired", HttpStatus.FORBIDDEN),

    ADDRESS_NOT_FOUND("Address not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("category not found", HttpStatus.NOT_FOUND),
    COUPON_NOT_FOUND("coupon not found", HttpStatus.NOT_FOUND),
    COUPON_CODE_ALREADY_EXISTS("COUPON_CODE_ALREADY_EXISTS", HttpStatus.BAD_REQUEST),
    COUPON_CREATE_ERROR("invalid created data", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND("product not found", HttpStatus.NOT_FOUND),
    INVALID_SLUG("slug is invalid format", HttpStatus.BAD_REQUEST),
    MEDIA_NOT_FOUND("media not found", HttpStatus.NOT_FOUND),
    MEDIA_NOT_REMOVED("Media remove failed", HttpStatus.CONFLICT),
    CART_ITEM_NOT_FOUND("ITEM NOT FOUND", HttpStatus.NOT_FOUND),
    VARIANT_NOT_FOUND("Variant not found", HttpStatus.NOT_FOUND),
    STOCK_IS_ENDED("CAn not be found in stock", HttpStatus.NOT_FOUND),
    USER_NOT_ACTIVATED("User not activated", HttpStatus.FORBIDDEN),
    INVALID_ACTIVATION_TOKEN("Token expired or invalid", HttpStatus.BAD_REQUEST),
    USER_IS_LOCKED("Account is locked", HttpStatus.FORBIDDEN),
    COMMENT_NOT_FOUND("Comment not found", HttpStatus.NOT_FOUND),
    RATING_NOT_FOUND("Rating not found", HttpStatus.NOT_FOUND),
    FAVORITE_NOT_FOUND("favorite not found", HttpStatus.NOT_FOUND),
    INVALID_REFRESH_TOKEN("invalid refresh token", HttpStatus.BAD_REQUEST);

    private final String info;
    private final HttpStatus status;

    ExceptionType(String message, HttpStatus status) {
        this.info = message;
        this.status = status;
    }
}
