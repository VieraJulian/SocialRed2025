package com.socialred2025.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The class `UserUpdateRequestDTO` is a Java DTO (Data Transfer Object) with
 * fields for new username,
 * new email, old password, and new password, along with Lombok annotations for
 * getter, setter, no-args
 * constructor, and all-args constructor.
 */
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
