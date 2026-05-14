package com.kashif.user_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String house;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private Boolean isDefault;
}
