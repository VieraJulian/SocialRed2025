package com.socialred2025.identity.infrastructure.outputadapter;

import com.socialred2025.identity.application.dto.ApiUserResponseDTO;
import com.socialred2025.identity.application.dto.UserDTO;
import com.socialred2025.identity.application.dto.UserRegisterRequestDTO;
import com.socialred2025.identity.application.dto.UserRegisterResponseDTO;
import com.socialred2025.identity.infrastructure.outputport.IUserServicePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceAdapter implements IUserServicePort {

    private final IUserServiceAdapter iUserServiceAdapter;

    public UserServiceAdapter(IUserServiceAdapter iUserServiceAdapter) {
        this.iUserServiceAdapter = iUserServiceAdapter;
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        ApiUserResponseDTO<UserDTO> response = iUserServiceAdapter.getUserByUsername(username);
        return Optional.ofNullable(response.getData());
    }

    @Override
    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        ApiUserResponseDTO<UserRegisterResponseDTO> response = iUserServiceAdapter.userRegister(userRegisterRequestDTO);
        return response.getData();
    }
}
