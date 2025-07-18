package com.thanh.user_managerment.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    @NotNull(message = "Username is required!")
    @Size(message = "Username must be less than 100 characters!", max = 100)
    @NotNull(message = "Password must not be empty!")
    private String username;

    @NotBlank(message = "Password is empty!")
    @Size(max = 100, min = 7, message = "Password must be between 7 and 100 characters!")
    private String password;

    @NotBlank(message = "Email is empty!")
    @Email(message = "Email must be valid!")
    private String email;
}
