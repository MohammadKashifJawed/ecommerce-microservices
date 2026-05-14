package com.kashif.user_service.service;

import com.kashif.user_service.dto.UserRequest;
import com.kashif.user_service.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> fetchAllUsers();

    UserResponse getUserById(Long userId);

    UserResponse createUser(UserRequest request);

    UserResponse updatedUser(Long userId, UserRequest request);

    void deleteUser(Long userId);
}
