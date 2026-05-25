package com.kashif.order_service.feign;

import com.kashif.order_service.dto.ApiResponse;
import com.kashif.order_service.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CART-SERVICE", path = "api/v1/cart")
public interface CartFeignClient {

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<CartResponse>> getCartById(@PathVariable String userId);

    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponse<String>> deleteCart(@PathVariable String userId);
}
