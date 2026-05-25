package com.kashif.product_service.service;

import com.kashif.product_service.dto.ProductRequest;
import com.kashif.product_service.dto.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest request);

    ProductResponse updateProduct(ProductRequest request, Long id);

    ProductResponse getProductById(Long aLong);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Long id);

    List<ProductResponse> getProductsByFilter(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice);
}
