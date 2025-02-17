package com.socialred2025.identity.infrastructure.inputAdapter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.socialred2025.identity.application.dto.AuthLoginRequestDTO;
import com.socialred2025.identity.application.service.UserDetailsServiceImpl;
import com.socialred2025.identity.infrastructure.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtils jwtUtils;

    public AuthController(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(userDetailsService.login(userRequest), HttpStatus.OK);
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
