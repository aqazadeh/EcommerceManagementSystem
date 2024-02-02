package com.aqazadeh.ecommerce.exception;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 19:15
 */

public class ApplicationException extends RuntimeException{

    public ApplicationException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
    }
}
