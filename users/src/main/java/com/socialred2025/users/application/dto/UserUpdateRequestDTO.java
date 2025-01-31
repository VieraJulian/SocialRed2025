package com.socialred2025.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {

    private String newUsername;

    private String newEmail;

    private String oldPassword;

    private String newPassword;

}
