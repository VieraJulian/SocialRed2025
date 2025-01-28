package com.socialred2025.users.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String newUsername;

    @Email(message = "Email should be valid")
    private String newEmail;

    private String oldPassword;

    @Size(min = 8, message = "Password should have at least 8 characters")
    private String newPassword;

}
