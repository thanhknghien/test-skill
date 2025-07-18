package com.thanh.user_managerment.service;

import com.thanh.user_managerment.dto.request.LoginRequest;
import com.thanh.user_managerment.dto.request.RegisterRequest;
import com.thanh.user_managerment.dto.request.UpdateUserRequest;
import com.thanh.user_managerment.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(RegisterRequest request);
    UserResponse login(LoginRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(UpdateUserRequest request);
    void deleteUser(Long id);
}
