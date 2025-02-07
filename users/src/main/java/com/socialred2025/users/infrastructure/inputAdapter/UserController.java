package com.socialred2025.users.infrastructure.inputAdapter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socialred2025.users.application.dto.ApiUserResponseDTO;
import com.socialred2025.users.application.dto.UserCreateRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.dto.UserUpdateRequestDTO;
import com.socialred2025.users.infrastructure.inputPort.IUserInputPort;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * The UserController class in Java defines REST endpoints for user creation,
 * retrieval, deletion, and
 * updating with error handling.
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private IUserInputPort iUserInputPort;

    public UserController(IUserInputPort iUserInputPort) {
        this.iUserInputPort = iUserInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiUserResponseDTO<?>> createUser(
            @Valid @RequestBody UserCreateRequestDTO createRequestDto) {
        try {
            UserResponseDTO userResponseDTO = iUserInputPort.createUser(createRequestDto);

            ApiUserResponseDTO<UserResponseDTO> response = ApiUserResponseDTO.<UserResponseDTO>builder()
                    .success(true)
                    .data(userResponseDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiUserResponseDTO<?>> getUserDetail(@PathVariable Long id) {
        try {
            UserResponseDTO userResponseDTO = iUserInputPort.findUserById(id);

            ApiUserResponseDTO<UserResponseDTO> response = ApiUserResponseDTO.<UserResponseDTO>builder()
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiUserResponseDTO<?>> deleteUser(@PathVariable Long id) {
        try {
            String msg = iUserInputPort.deleteUser(id);

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(true)
                    .data(msg)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiUserResponseDTO<?>> updateUser(@PathVariable Long id,
            @ModelAttribute UserUpdateRequestDTO updateRequestDTO,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            UserResponseDTO userResponseDTO = iUserInputPort.updateUser(id, updateRequestDTO, file);

            ApiUserResponseDTO<UserResponseDTO> response = ApiUserResponseDTO.<UserResponseDTO>builder()
                    .success(true)
                    .data(userResponseDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
