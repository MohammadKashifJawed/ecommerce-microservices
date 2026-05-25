package com.kashif.order_service.exception;

import com.kashif.order_service.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(
            OrderNotFoundException exception,
            HttpServletRequest request
    ){
        return new ResponseEntity<>(new ErrorResponse(
                exception.getMessage(),
                request.getContextPath()
        ), HttpStatus.NOT_FOUND);
    }
}
