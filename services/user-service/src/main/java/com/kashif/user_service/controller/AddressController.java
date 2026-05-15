package com.kashif.user_service.controller;

import com.kashif.user_service.dto.AddressRequest;
import com.kashif.user_service.dto.AddressResponse;
import com.kashif.user_service.dto.ApiResponse;
import com.kashif.user_service.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse<AddressResponse>> addAddress(
            @RequestBody AddressRequest request, @PathVariable String userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Address created successfully",
                        service.createAddress(request, Long.valueOf(userId))
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> updateAddress(
            @RequestBody AddressRequest request, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Address updated successfully",
                        service.updateAddress(request, Long.valueOf(id))
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AddressResponse>> getAddress(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Address fetched successfully",
                        service.getAddressById(Long.valueOf(id))
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<AddressResponse>>> getAllAddress(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        "Addresses fetched successfully",
                        service.getAllAddress(Long.valueOf(userId))
                )
        );
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String addressId){
        service.deleteAddress(Long.valueOf(addressId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ApiResponse<>(
                        "Address deleted successfully",
                        null
                )
        );
    }
}
