package com.gohenry.javabankingapiahqlle.common.controller;

import com.gohenry.javabankingapiahqlle.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// todo proper responses
@RestControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        return e.getMessage();
    }
}
