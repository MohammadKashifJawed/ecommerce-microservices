package com.kashif.cart_service.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(String s) {
        super(s);
    }
}
