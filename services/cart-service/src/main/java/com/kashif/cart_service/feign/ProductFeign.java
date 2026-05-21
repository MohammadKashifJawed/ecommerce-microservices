package com.kashif.cart_service.feign;

import com.kashif.cart_service.dto.ApiResponse;
import com.kashif.cart_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE", path = "/api/v1/product")
public interface ProductFeign {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable String id);
}
