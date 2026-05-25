package com.kashif.order_service.feign;

import com.kashif.order_service.dto.AddressResponse;
import com.kashif.order_service.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE", path = "api/v1/address")
public interface UserFeignClient {

    @GetMapping
    public ResponseEntity<ApiResponse<AddressResponse>> getAddress(
            @RequestParam("addressId") String addressId,
            @RequestParam("userId") String userId
    );
}
