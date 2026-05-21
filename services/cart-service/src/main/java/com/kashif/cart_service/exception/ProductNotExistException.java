package com.kashif.cart_service.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String s) {
        super(s);
    }
}
