package com.socialred2025.identity.infrastructure.outputAdapter;

import com.socialred2025.identity.application.dto.ApiUserResponseDTO;
import com.socialred2025.identity.application.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service")
public interface IUserServiceAdapter {

    @GetMapping("/internal/users/{username}")
    public ApiUserResponseDTO<UserDTO> getUserByUsername(@PathVariable String username);
}
