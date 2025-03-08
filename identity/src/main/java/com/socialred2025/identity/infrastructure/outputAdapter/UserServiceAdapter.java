package com.socialred2025.identity.infrastructure.outputAdapter;

import com.socialred2025.identity.application.dto.ApiUserResponseDTO;
import com.socialred2025.identity.application.dto.UserDTO;
import com.socialred2025.identity.application.dto.UserRegisterRequestDTO;
import com.socialred2025.identity.application.dto.UserRegisterResponseDTO;
import com.socialred2025.identity.infrastructure.outputPort.IUserServicePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceAdapter implements IUserServicePort {

    private final IUserServiceAdapter userServiceAdapter;

    public UserServiceAdapter(IUserServiceAdapter iUserServiceAdapter) {
        this.userServiceAdapter = iUserServiceAdapter;
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        ApiUserResponseDTO<UserDTO> response = userServiceAdapter.getUserByUsername(username);
        return Optional.ofNullable((UserDTO) response.getData());
    }

    @Override
    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        ApiUserResponseDTO<UserRegisterResponseDTO> response = userServiceAdapter.userRegister(userRegisterRequestDTO);
        return response.getData();
    }
}
