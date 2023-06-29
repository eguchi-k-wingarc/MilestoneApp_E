package com.example.mils.demo.web.error;

import java.nio.file.AccessDeniedException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleBadRequestException(HttpMessageNotReadableException e) {
        return "error/400";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException e) {
        return "error/403";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundException(NoHandlerFoundException e) {
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "error/500";
    }
}
