package com.kashif.user_service.service.impl;

import com.kashif.user_service.dto.AddressRequest;
import com.kashif.user_service.dto.AddressResponse;
import com.kashif.user_service.exception.AddressNotFoundException;
import com.kashif.user_service.exception.UserNotFoundException;
import com.kashif.user_service.mapper.AddressMapper;
import com.kashif.user_service.model.Address;
import com.kashif.user_service.model.User;
import com.kashif.user_service.repository.AddressRepository;
import com.kashif.user_service.repository.UserRepository;
import com.kashif.user_service.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final UserRepository userRepository;
    private final AddressMapper mapper;

    @Override
    public AddressResponse createAddress(AddressRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with given id not found")
        );
        return mapper.addressToAddressResponse(
                repository.save(
                        mapper.addressRequestToAddress(request, userId)
                )
        );
    }

    @Override
    public AddressResponse updateAddress(AddressRequest request, Long addressId) {
        Address address = repository.findById(addressId).orElseThrow(
                () -> new AddressNotFoundException("Address with given id not found")
        );
        address.setHouse(request.getHouse());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPincode(request.getPincode());
        address.setIsDefault(request.getIsDefault());
        return mapper.addressToAddressResponse(repository.save(address));
    }

    @Override
    public AddressResponse getAddressById(Long addressId, Long userId) {
        Address address = repository.findByIdAndUserId(addressId, userId).orElseThrow(
                () -> new AddressNotFoundException("Address with given id not found")
        );
        return mapper.addressToAddressResponse(address);
    }

    @Override
    public List<AddressResponse> getAllAddress(Long userId) {
        return mapper.addressListToAddressResponseList(
                repository.findByUserId(userId)
        );
    }

    @Override
    public void deleteAddress(Long addressId) {
        repository.delete(
                repository.findById(addressId).orElseThrow(
                        () -> new AddressNotFoundException("Address with given id not found")
                )
        );
    }
}
