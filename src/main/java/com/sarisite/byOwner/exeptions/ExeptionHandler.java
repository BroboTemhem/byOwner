package com.sarisite.byOwner.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarNotFoundExeption.class)
    public ResponseEntity<Object> handleCarNotFound(CarNotFoundExeption ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), LocalDateTime.now(), NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(BrandNotFoundExeption.class)
    public ResponseEntity<Object> handleBrandNotFound(BrandNotFoundExeption ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), LocalDateTime.now(), NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(ColorNotFoundExeption.class)
    public ResponseEntity<Object> handleColorNotFound(ColorNotFoundExeption ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), LocalDateTime.now(), NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(ModelNotFoundExeption.class)
    public ResponseEntity<Object> handleModelNotFound(ModelNotFoundExeption ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), LocalDateTime.now(), NOT_FOUND), NOT_FOUND);
    }
}