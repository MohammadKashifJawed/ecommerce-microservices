package com.kashif.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponse {
    private Long userId;
    private BigDecimal totalPrice;
    private List<CartItemResponse> items;
}
