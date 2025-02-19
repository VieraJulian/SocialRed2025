package com.socialred2025.users.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The class `UserCreateRequestDTO` is a data transfer object used for creating
 * a new user with fields
 * for username, email, password, and roleId.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

}
