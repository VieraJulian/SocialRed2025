package com.socialred2025.users.infrastructure.inputPort;

import com.socialred2025.users.application.dto.InternalUserResponseDTO;
import com.socialred2025.users.application.exception.UserNotFoundException;

public interface IInternalUserInputPort {

    InternalUserResponseDTO getUser(String username) throws UserNotFoundException;
}
