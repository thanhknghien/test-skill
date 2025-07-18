package com.thanh.user_managerment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username is required!")
    @Size(max = 100, min = 1, message = "Username must be less than 100 characters and greater than 1 character!")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Password is required!")
    private String password;

    @Size(max = 100, message = "Email must be less than 100 characters!")
    @Email(message = "Email must be valid!")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Role is required!")
    private Role role;
}

enum Role {
    ADMIN, USER;
}
