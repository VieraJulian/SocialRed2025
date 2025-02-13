package com.socialred2025.identity.infrastructure.inputAdapter;

import com.socialred2025.identity.application.dto.AuthLoginRequestDTO;
import com.socialred2025.identity.application.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(userDetailsService.login(userRequest), HttpStatus.OK);
    }
}
