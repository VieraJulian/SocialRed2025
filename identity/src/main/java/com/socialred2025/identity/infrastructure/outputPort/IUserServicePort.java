package com.socialred2025.identity.infrastructure.outputPort;

import com.socialred2025.identity.application.dto.UserDTO;
import com.socialred2025.identity.application.dto.UserRegisterRequestDTO;
import com.socialred2025.identity.application.dto.UserRegisterResponseDTO;

import java.util.Optional;

public interface IUserServicePort {

    Optional<UserDTO> getUserByUsername(String username);

    UserRegisterResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO);
}
