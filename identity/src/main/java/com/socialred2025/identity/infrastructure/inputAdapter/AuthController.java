package com.socialred2025.identity.infrastructure.inputAdapter;

import com.socialred2025.identity.application.dto.*;
import com.socialred2025.identity.application.service.IdentityService;
import com.socialred2025.identity.infrastructure.inputPort.IIdentityInputPort;
import com.socialred2025.identity.infrastructure.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final IIdentityInputPort identityInputPort;

    private final JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils, IdentityService identityService, IIdentityInputPort identityInputPort) {
        this.jwtUtils = jwtUtils;
        this.identityInputPort = identityInputPort;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiAuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO userRequest) {
        try {
            LoginResponseDTO loginResponseDTO = identityInputPort.login(userRequest);

            ApiAuthResponseDTO<LoginResponseDTO> response = ApiAuthResponseDTO.<LoginResponseDTO>builder()
                    .success(true)
                    .data(loginResponseDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error login user: {}", e.getMessage());

            ApiAuthResponseDTO<String> response = ApiAuthResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiAuthResponseDTO<?>> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        try {
            UserRegisterResponseDTO registerResponse = identityInputPort.register(userRegisterRequestDTO);

            ApiAuthResponseDTO<UserRegisterResponseDTO> response = ApiAuthResponseDTO.<UserRegisterResponseDTO>builder()
                    .success(true)
                    .data(registerResponse)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error register user: {}", e.getMessage());

            ApiAuthResponseDTO<String> response = ApiAuthResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<ApiAuthResponseDTO> validateToken(@RequestParam String token) {
        try {
            TokenValidationResultDTO validationResultDTO = jwtUtils.validateToken(token);

            ApiAuthResponseDTO<TokenValidationResultDTO> response = ApiAuthResponseDTO.<TokenValidationResultDTO>builder()
                    .success(true)
                    .data(validationResultDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error validate token: {}", e.getMessage());

            ApiAuthResponseDTO<String> response = ApiAuthResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
