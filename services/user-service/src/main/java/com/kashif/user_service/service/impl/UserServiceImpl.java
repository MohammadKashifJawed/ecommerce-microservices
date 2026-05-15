package com.kashif.user_service.service.impl;

import com.kashif.user_service.dto.UserRequest;
import com.kashif.user_service.exception.UserAlreadyExistException;
import com.kashif.user_service.exception.UserNotFoundException;
import com.kashif.user_service.mapper.UserMapper;
import com.kashif.user_service.model.User;
import com.kashif.user_service.repository.UserRepository;
import com.kashif.user_service.dto.UserResponse;
import com.kashif.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<UserResponse> fetchAllUsers() {
        return UserMapper.userListToUserResponseList(repository.findAll());
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return UserMapper.userToUserResponse(
                repository.findById(userId).orElseThrow(
                        () -> new UserNotFoundException(
                                "User with given id not found"
                        )
                )
        );
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = repository.findByEmail(request.getEmail());
        if (user != null){
            throw new UserAlreadyExistException("User already exist for this email");
        }
        return UserMapper.userToUserResponse(
                repository.save(
                        UserMapper.userRequestToUser(request)
                )
        );
    }

    @Transactional
    @Override
    public UserResponse updatedUser(Long userId, UserRequest request) {
        User user = repository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with given id not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setProfileImage(request.getProfileImage());
        return UserMapper.userToUserResponse(repository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        User user = repository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with given id not found"));
        repository.delete(user);
    }
}
