package com.kashif.order_service.dto;

import com.kashif.order_service.model.OrderItem;
import com.kashif.order_service.model.OrderStatus;
import com.kashif.order_service.model.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String paymentStatus;
    private Long addressId;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
}
