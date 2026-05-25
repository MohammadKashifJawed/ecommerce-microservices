package com.kashif.user_service.service;

import com.kashif.user_service.dto.AddressRequest;
import com.kashif.user_service.dto.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(AddressRequest request, Long userId);

    AddressResponse updateAddress(AddressRequest request, Long addressId);

    AddressResponse getAddressById(Long addressId , Long userId);

    List<AddressResponse> getAllAddress(Long userId);

    void deleteAddress(Long addressId);
}
