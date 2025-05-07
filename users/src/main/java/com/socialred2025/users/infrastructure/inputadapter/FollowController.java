package com.socialred2025.users.infrastructure.inputadapter;

import com.socialred2025.users.application.dto.ApiUserResponseDTO;
import com.socialred2025.users.application.dto.FollowDTO;
import com.socialred2025.users.application.dto.FollowRequestDTO;
import com.socialred2025.users.infrastructure.inputport.IFollowInputPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@Slf4j
public class FollowController {

    private final IFollowInputPort iFollowInputPort;

    public FollowController(IFollowInputPort iFollowInputPort) {
        this.iFollowInputPort = iFollowInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiUserResponseDTO<?>> createFollow(@RequestHeader("userId") Long userId,
            @Valid @RequestBody FollowRequestDTO followRequestDTO) {
        try {
            FollowDTO followDTO = iFollowInputPort.createFollow(userId, followRequestDTO);

            ApiUserResponseDTO<FollowDTO> response = ApiUserResponseDTO.<FollowDTO>builder()
                    .success(true)
                    .data(followDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating follow: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
