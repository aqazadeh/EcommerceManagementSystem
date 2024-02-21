package com.aqazadeh.ecommerce.exception;

import lombok.Getter;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 19:15
 */
@Getter
public class ApplicationException extends RuntimeException {
    private ExceptionType exceptionType;

    public ApplicationException(ExceptionType exceptionType) {
        super(exceptionType.getInfo());
        this.exceptionType = exceptionType;
    }
}
