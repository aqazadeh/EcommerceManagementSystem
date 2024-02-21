package com.aqazadeh.ecommerce.exception;

import com.aqazadeh.ecommerce.dto.response.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 18.02.2024
 * Time: 12:05
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionDto> handle(ApplicationException exception) {
        List<String> info = List.of(exception.getExceptionType().getInfo());
        ExceptionDto exceptionDto = new ExceptionDto(exception.getExceptionType().getStatus().value(), info);
        return ResponseEntity.status(exception.getExceptionType().getStatus()).body(exceptionDto);

    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionDto> handle(Exception exception) {
//        log.error(exception.getMessage());
//        return null;
//    }
}
