package com.kashif.cart_service.controller;

import com.kashif.cart_service.dto.ApiResponse;
import com.kashif.cart_service.dto.CartRequest;
import com.kashif.cart_service.dto.CartResponse;
import com.kashif.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CartResponse>> addToCart(@RequestBody CartRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Cart created successfully",
                        service.addToCart(request)
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CartResponse>> updateCart(@RequestBody CartRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Cart updated successfully",
                        service.updateCart(request)
                )
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> getCartById(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Cart fetched successfully",
                        service.getCart(Long.parseLong(userId))
                )
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteCart(@PathVariable String userId){
        service.deleteCart(Long.parseLong(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Cart deleted successfully",
                        null
                )
        );
    }
}
