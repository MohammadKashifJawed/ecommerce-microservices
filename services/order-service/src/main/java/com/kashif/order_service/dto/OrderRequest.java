package com.kashif.order_service.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String userId;
    private String addressId;
    private String paymentStatus;
}
