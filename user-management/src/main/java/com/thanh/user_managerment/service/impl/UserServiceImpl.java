package com.thanh.user_managerment.service.impl;

import com.thanh.user_managerment.dto.request.LoginRequest;
import com.thanh.user_managerment.dto.request.RegisterRequest;
import com.thanh.user_managerment.dto.request.UpdateUserRequest;
import com.thanh.user_managerment.dto.response.UserResponse;
import com.thanh.user_managerment.entity.User;
import com.thanh.user_managerment.exception.EmailAlreadyExistsException;
import com.thanh.user_managerment.exception.InvalidPasswordException;
import com.thanh.user_managerment.exception.UserAlreadyExistsException;
import com.thanh.user_managerment.exception.UserNotFoundException;
import com.thanh.user_managerment.mapper.UserMapper;
import com.thanh.user_managerment.repository.UserRepository;
import com.thanh.user_managerment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse register(RegisterRequest request) {
        userRepository.existByEmail(request.getEmail())
                .orElseThrow(
                        () -> new EmailAlreadyExistsException("Email: " + request.getEmail() + " already exists!")
                );

        userRepository.existByUsername(request.getUsername())
                .orElseThrow(
                        () -> new UserAlreadyExistsException("Username: "+ request.getUsername() + "already exist!")
                );

        return UserMapper.toResponse(userRepository.save(UserMapper.fromRegisterRequest(request)));
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = userRepository.existByUsername(request.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException("User with username: " + request.getUsername() + " not found!")
                );

        User currentUser = UserMapper.fromLoginRequest(request);
        if (!currentUser.getPassword().equals(user.getPassword())){
            throw new InvalidPasswordException("Incorrect Password!");
        }

        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User with id:" + " not found!")
                );

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with email: " + request.getEmail())
                );

        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                        .orElseThrow(
                                () -> new UserNotFoundException("User not found!")
                        );
        userRepository.deleteById(id);
    }
}
