package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.application.dto.ApiUserResponseDTO;
import com.socialred2025.publications.infrastructure.outputport.IUserServicePort;
import org.springframework.stereotype.Component;

@Component
public class UserServiceAdapter implements IUserServicePort {

    private final IUserServiceAdapter iUserServiceAdapter;

    public UserServiceAdapter(IUserServiceAdapter iUserServiceAdapter) {
        this.iUserServiceAdapter = iUserServiceAdapter;
    }

    @Override
    public Boolean existsUserById(Long userId) {
        ApiUserResponseDTO<Boolean> response = iUserServiceAdapter.existsUserById(userId);
        return response.getData();
    }
}
