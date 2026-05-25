package com.kashif.user_service.controller;

import com.kashif.user_service.dto.ApiResponse;
import com.kashif.user_service.dto.UserRequest;
import com.kashif.user_service.dto.UserResponse;
import com.kashif.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "User fetched successfully",
                service.fetchAllUsers()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "User fetched successfully",
                service.getUserById(Long.valueOf(id))
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "User created successfully",
                service.createUser(request)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable String id,
            @RequestBody UserRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                "User updated successfully",
                service.updatedUser(Long.valueOf(id), request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String id){
        service.deleteUser(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                "User deleted successfully",
                null
        ));
    }
}
