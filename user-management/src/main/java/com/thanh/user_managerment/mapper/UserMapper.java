package com.thanh.user_managerment.mapper;

import com.thanh.user_managerment.dto.request.LoginRequest;
import com.thanh.user_managerment.dto.request.RegisterRequest;
import com.thanh.user_managerment.dto.request.UpdateUserRequest;
import com.thanh.user_managerment.dto.response.UserResponse;
import com.thanh.user_managerment.entity.User;

public class UserMapper {
    public static User fromLoginRequest(LoginRequest request){
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    public static User fromRegisterRequest(RegisterRequest request){
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public static User fromUpdateRequest(UpdateUserRequest request){
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
