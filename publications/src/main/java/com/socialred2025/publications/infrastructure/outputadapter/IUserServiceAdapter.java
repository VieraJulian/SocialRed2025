package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.application.dto.ApiUserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service")
public interface IUserServiceAdapter {

    @GetMapping("/internal/users/isValid/{userId}")
    public ApiUserResponseDTO<Boolean> existsUserById(@PathVariable Long userId);
}
