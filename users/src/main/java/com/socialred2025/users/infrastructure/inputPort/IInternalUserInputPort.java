package com.socialred2025.users.infrastructure.inputPort;

import com.socialred2025.users.application.dto.InternalUserResponseDTO;
import com.socialred2025.users.application.dto.UserRegisterRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.exception.RoleNotFoundException;
import com.socialred2025.users.application.exception.UserNotFoundException;

public interface IInternalUserInputPort {

    InternalUserResponseDTO getUser(String username) throws UserNotFoundException;

    UserResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO) throws RoleNotFoundException;
}
