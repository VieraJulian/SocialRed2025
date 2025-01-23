package com.socialred2025.users.application.dto;

import com.socialred2025.users.domain.Image;
import com.socialred2025.users.domain.Role;

import lombok.*;

/**
 * The `UserResponseDTO` class represents a data transfer object for user information including id,
 * username, email, account status flags, role, and image.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private boolean enabled;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;
    private Role role;
    private Image image;
}
