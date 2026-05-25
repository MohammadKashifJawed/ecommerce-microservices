package com.kashif.product_service.dto;

import com.kashif.product_service.feign.UserResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private Long id;
    private Long productId;
    private UserResponse user;
    private Double rating;
    private String comment;
}
