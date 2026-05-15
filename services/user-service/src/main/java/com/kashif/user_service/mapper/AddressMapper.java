package com.kashif.user_service.mapper;

import com.kashif.user_service.dto.AddressRequest;
import com.kashif.user_service.dto.AddressResponse;
import com.kashif.user_service.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {

    public Address addressRequestToAddress(AddressRequest request, Long userId){
        return Address.builder()
                .userId(userId)
                .house(request.getHouse())
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .country(request.getCountry())
                .isDefault(request.getIsDefault())
                .build();
    }

    public AddressResponse addressToAddressResponse(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .userId(address.getUserId())
                .house(address.getHouse())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .country(address.getCountry())
                .isDefault(address.getIsDefault())
                .build();
    }

    public List<AddressResponse> addressListToAddressResponseList(List<Address> addresses){
        List<AddressResponse> responseList = new ArrayList<>();
        for (Address address : addresses){
            responseList.add(
                    AddressResponse.builder()
                            .id(address.getId())
                            .userId(address.getUserId())
                            .house(address.getHouse())
                            .street(address.getStreet())
                            .city(address.getCity())
                            .state(address.getState())
                            .pincode(address.getPincode())
                            .country(address.getCountry())
                            .isDefault(address.getIsDefault())
                            .build()
            );
        }
        return responseList;
    }
}
