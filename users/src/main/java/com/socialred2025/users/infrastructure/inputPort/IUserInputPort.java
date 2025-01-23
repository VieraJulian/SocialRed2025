package com.socialred2025.users.infrastructure.inputPort;

import com.socialred2025.users.application.dto.UserCreateRequestDto;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.exception.RoleNotFoundException;

public interface IUserInputPort {

    UserResponseDTO createUser(UserCreateRequestDto createRequestDto) throws RoleNotFoundException;
}
