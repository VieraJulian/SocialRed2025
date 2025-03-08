package com.socialred2025.identity.infrastructure.inputPort;

import com.socialred2025.identity.application.dto.LoginRequestDTO;
import com.socialred2025.identity.application.dto.LoginResponseDTO;
import com.socialred2025.identity.application.dto.UserRegisterRequestDTO;
import com.socialred2025.identity.application.dto.UserRegisterResponseDTO;

public interface IIdentityInputPort {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO);
}
