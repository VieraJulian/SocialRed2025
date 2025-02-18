package com.socialred2025.identity.infrastructure.inputAdapter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.socialred2025.identity.application.dto.ApiAuthResponseDTO;
import com.socialred2025.identity.application.dto.LoginRequestDTO;
import com.socialred2025.identity.application.dto.LoginResponseDTO;
import com.socialred2025.identity.application.service.UserDetailsServiceImpl;
import com.socialred2025.identity.infrastructure.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtils jwtUtils;

    public AuthController(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiAuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO userRequest) {
        try {
            LoginResponseDTO loginResponseDTO = userDetailsService.login(userRequest);

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

    @PostMapping("/validateToken")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
        DecodedJWT decodedJWT = jwtUtils.validateToken(token);

        if (decodedJWT == null) {
            return ResponseEntity.ok(Map.of("valid", false));
        }

        String username = jwtUtils.extractUsername(decodedJWT);
        String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("username", username);
        response.put("authorities", authorities);

        return ResponseEntity.ok(response);
    }
}
