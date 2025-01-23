package com.socialred2025.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The class `UserCreateRequestDto` represents a data transfer object for creating a user with fields
 * for username, email, password, and roleId.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    private String username;
    private String email;
    private String password;
    private Long roleId;

}
