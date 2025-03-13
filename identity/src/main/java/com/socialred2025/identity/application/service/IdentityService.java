package com.socialred2025.identity.application.service;

import com.socialred2025.identity.application.dto.LoginRequestDTO;
import com.socialred2025.identity.application.dto.LoginResponseDTO;
import com.socialred2025.identity.application.dto.UserRegisterRequestDTO;
import com.socialred2025.identity.application.dto.UserRegisterResponseDTO;
import com.socialred2025.identity.infrastructure.inputport.IIdentityInputPort;
import com.socialred2025.identity.infrastructure.outputport.IUserServicePort;
import com.socialred2025.identity.infrastructure.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IdentityService implements IIdentityInputPort {

    private final IUserServicePort userServicePort;

    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public IdentityService(IUserServicePort userServicePort, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userServicePort = userServicePort;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public LoginResponseDTO login(LoginRequestDTO userRequest) {
        String username = userRequest.username();
        String password = userRequest.password();

        Authentication authentication = this.authenticate(username, password);

        String accessToken = jwtUtils.createToken(authentication);
        return LoginResponseDTO.builder()
                .username(username)
                .message("Login ok")
                .jwt(accessToken)
                .status(true)
                .build();
    }

    public UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO) {
        return userServicePort.registerUser(userRegisterRequestDTO);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw  new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
