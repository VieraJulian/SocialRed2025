package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.application.dto.ApiUserResponseDTO;
import com.socialred2025.publications.application.dto.FriendDTO;
import com.socialred2025.publications.application.dto.FriendStatusRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "users-service")
public interface IUserServiceAdapter {

    @GetMapping("/internal/users/isValid/{userId}")
    public ApiUserResponseDTO<Boolean> existsUserById(@PathVariable Long userId);

    @GetMapping("/friends")
    public ApiUserResponseDTO<List<FriendDTO>> getAllFriends(@RequestHeader("userId") Long userId, @RequestParam("status") String status);
}
