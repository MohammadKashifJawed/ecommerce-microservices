package com.kashif.cart_service.dto;

import com.kashif.cart_service.model.CartItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CartResponse {
    private Long id;
    private Long userId;
    private List<CartItemResponse> items = new ArrayList<>();
    private BigDecimal totalPrice;
}
