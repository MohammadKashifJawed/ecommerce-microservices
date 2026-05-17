package com.kashif.product_service.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long productId;
    private Long userId;
    private Double rating;
    private String comment;
}
