package com.kashif.user_service.mapper;

import com.kashif.user_service.dto.UserRequest;
import com.kashif.user_service.dto.UserResponse;
import com.kashif.user_service.model.Role;
import com.kashif.user_service.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<UserResponse> userListToUserResponseList(List<User> userList){
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : userList){
            userResponseList.add(
                    UserResponse.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .profileImage(user.getProfileImage())
                            .build()
            );
        }
        return userResponseList;
    }

    public static UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profileImage(user.getProfileImage())
                .build();
    }

    public static User userRequestToUser(UserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .profileImage(request.getProfileImage())
                .role(Role.valueOf("USER"))
                .enabled(true)
                .build();
    }
}
