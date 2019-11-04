package com.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

//@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails usernnameNotFoundException(UsernameNotFoundException ex){
        return new CustomErrorDetails(LocalDateTime.now(),"From @RestController NOT FOUND",ex.getMessage());
    }
}
