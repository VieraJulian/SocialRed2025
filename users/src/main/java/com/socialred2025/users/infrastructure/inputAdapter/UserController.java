package com.socialred2025.users.infrastructure.inputAdapter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialred2025.users.application.dto.ApiUserResponseDTO;
import com.socialred2025.users.application.dto.UserCreateRequestDto;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.infrastructure.inputPort.IUserInputPort;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
            @RequestBody UserCreateRequestDto createRequestDto) {
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

}
