package com.kashif.product_service.exception;

public class CategoryNotExistException extends RuntimeException {
    public CategoryNotExistException(String s) {
        super(s);
    }
}
