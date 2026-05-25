package com.kashif.product_service.feign;

import lombok.Data;

@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String profileImage;
}
