package com.kashif.user_service.dto;

import lombok.Data;

@Data
public class AddressRequest {
    private String house;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private Boolean isDefault;
}
