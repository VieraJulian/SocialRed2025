package com.socialred2025.identity.application.service;

import com.socialred2025.identity.application.dto.AuthLoginRequestDTO;
import com.socialred2025.identity.application.dto.AuthResponseDTO;
import com.socialred2025.identity.application.dto.UserDTO;
import com.socialred2025.identity.infrastructure.outputPort.IUserServicePort;
import com.socialred2025.identity.infrastructure.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserServicePort userServicePort;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(IUserServicePort iUserServicePort, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userServicePort = iUserServicePort;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO user = userServicePort.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().toString())));

        user.getRole().getPermissions().stream()
                .forEach(permissionDTO -> authorityList.add(new SimpleGrantedAuthority(permissionDTO.getPermissionName().toString())));

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNotExpired(),
                user.isCredentialNotExpired(),
                user.isAccountNotLocked(),
                authorityList
        );
    }

    public AuthResponseDTO login(AuthLoginRequestDTO userRequest) {
        String username = userRequest.username();
        String password = userRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToke(authentication);
        return AuthResponseDTO.builder()
                .username(username)
                .message("Login ok")
                .jwt(accessToken)
                .status(true)
                .build();
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw  new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
