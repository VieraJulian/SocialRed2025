package com.socialred2025.identity.application.service;

import com.socialred2025.identity.application.dto.LoginRequestDTO;
import com.socialred2025.identity.application.dto.LoginResponseDTO;
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

    public UserDetailsServiceImpl(IUserServicePort iUserServicePort) {
        this.userServicePort = iUserServicePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO user = userServicePort.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getRoleName().toString())));

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
}
