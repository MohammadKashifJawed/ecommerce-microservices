package com.kashif.product_service.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String s) {
        super(s);
    }
}
