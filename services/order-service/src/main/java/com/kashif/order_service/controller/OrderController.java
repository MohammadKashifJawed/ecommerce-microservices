package com.kashif.order_service.controller;

import com.kashif.order_service.dto.ApiResponse;
import com.kashif.order_service.dto.OrderRequest;
import com.kashif.order_service.dto.OrderResponse;
import com.kashif.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Order created successfully",
                        service.createOrder(request)
                )
        );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteOrder(
            @RequestParam String userId,
            @RequestParam String orderId
    ){
        service.deleteOrder(Long.parseLong(userId), Long.parseLong(orderId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Order cancelled successfully",
                        ""
                )
        );
    }
}
