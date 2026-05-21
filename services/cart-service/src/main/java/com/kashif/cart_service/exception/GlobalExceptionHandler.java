package com.kashif.cart_service.exception;

import com.kashif.cart_service.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleProductOutOfStockException(
            ProductOutOfStockException exception,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        exception.getMessage(),
                        request.getContextPath()
                )
        );
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartNotFoundException(
            CartNotFoundException exception,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        exception.getMessage(),
                        request.getContextPath()
                )
        );
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ErrorResponse> handleProductNotExistException(
            ProductNotExistException exception,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        exception.getMessage(),
                        request.getContextPath()
                )
        );
    }
}
