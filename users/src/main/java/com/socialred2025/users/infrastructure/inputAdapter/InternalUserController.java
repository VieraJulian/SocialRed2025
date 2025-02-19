package com.socialred2025.users.infrastructure.inputAdapter;

import com.socialred2025.users.application.dto.ApiUserResponseDTO;
import com.socialred2025.users.application.dto.InternalUserResponseDTO;
import com.socialred2025.users.application.dto.UserRegisterRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.infrastructure.inputPort.IInternalUserInputPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/users")
@Slf4j
public class InternalUserController {

    private final IInternalUserInputPort internalUserInputPort;

    public InternalUserController(IInternalUserInputPort internalUserInputPort) {
        this.internalUserInputPort = internalUserInputPort;
    }

    @GetMapping("/{username}")
    public ResponseEntity<ApiUserResponseDTO<?>> getUser(@PathVariable String username) {
        try {
            InternalUserResponseDTO userResponseDTO = internalUserInputPort.getUser(username);

            ApiUserResponseDTO<InternalUserResponseDTO> response = ApiUserResponseDTO.<InternalUserResponseDTO>builder()
                    .success(true)
                    .data(userResponseDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting user: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiUserResponseDTO<?>> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = internalUserInputPort.registerUser(userRegisterRequestDTO);

            ApiUserResponseDTO<UserResponseDTO> response = ApiUserResponseDTO.<UserResponseDTO>builder()
                    .success(true)
                    .data(userResponseDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            log.error("Error register user: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
