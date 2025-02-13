package com.socialred2025.identity.infrastructure.outputPort;

import com.socialred2025.identity.application.dto.UserDTO;

import java.util.Optional;

public interface IUserServicePort {

    Optional<UserDTO> getUserByUsername(String username);
}
