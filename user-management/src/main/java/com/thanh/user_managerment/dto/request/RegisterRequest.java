package com.thanh.user_managerment.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotNull(message = "Username is required!")
    @Size(message = "Username must be less than 100 characters!", max = 100)
    private String username;

    @NotNull(message = "Password is required!")
    private String password;

    @NotNull(message = "Email is required!")
    @Size(max = 100, message = "Email must be less than 100 characters!")
    @Email(message = "Email must be valid!")
    private String email;
}
