package com.kashif.order_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponse {
    private Long id;
    private Long productId;
    private String imageUrl;
    private Integer quantity;
    private BigDecimal price;
}
