package com.kashif.product_service.feign;

import com.kashif.product_service.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/api/v1/user")
public interface UserFeign {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable String id);
}
