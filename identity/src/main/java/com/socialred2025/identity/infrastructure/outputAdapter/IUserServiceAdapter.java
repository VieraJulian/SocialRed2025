package com.socialred2025.identity.infrastructure.outputAdapter;

import com.socialred2025.identity.application.dto.ApiUserResponseDTO;
import com.socialred2025.identity.application.dto.UserDTO;
import com.socialred2025.identity.application.dto.UserRegisterRequestDTO;
import com.socialred2025.identity.application.dto.UserRegisterResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-service")
public interface IUserServiceAdapter {

    @GetMapping("/internal/users/{username}")
    public ApiUserResponseDTO<UserDTO> getUserByUsername(@PathVariable String username);

    @PostMapping("/internal/users/register")
    public ApiUserResponseDTO<UserRegisterResponseDTO> userRegister(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO);
}
