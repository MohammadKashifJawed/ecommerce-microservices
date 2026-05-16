package com.kashif.product_service.exception;

import com.kashif.product_service.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotExistException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotExistException(
            CategoryNotExistException exception, HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        exception.getMessage(),
                        request.getPathInfo()
                )
        );
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ErrorResponse> handleProductNotExistException(
            ProductNotExistException exception, HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        exception.getMessage(),
                        request.getContextPath()
                )
        );
    }
}
