package com.socialred2025.users.application;

import com.socialred2025.users.application.dto.InternalUserResponseDTO;
import com.socialred2025.users.application.dto.UserRegisterRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.exception.RoleNotFoundException;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.mapper.IUserMapper;
import com.socialred2025.users.domain.Role;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputport.IInternalUserInputPort;
import com.socialred2025.users.infrastructure.outputport.IRoleRepository;
import com.socialred2025.users.infrastructure.outputport.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InternalUseCase implements IInternalUserInputPort {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final IUserMapper userMapper;

    public InternalUseCase(IUserRepository userRepository, IUserMapper userMapper, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public InternalUserResponseDTO getUser(String username) throws UserNotFoundException {
        UserEntity userDB = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        return userMapper.userEntityToInternalUserResponseDTO(userDB);
    }

    public UserResponseDTO registerUser(UserRegisterRequestDTO registerRequestDTO) throws RoleNotFoundException {
        UserEntity userRegisterInfo = userMapper.userRegisterRequestDtoToUserEntity(registerRequestDTO);
        Role role = roleRepository.getReferenceById(1L);

        if (userRegisterInfo.getEmail().contains("@admin.com")) {
            role = roleRepository.getReferenceById(2L);
        }

        if (!roleRepository.existsById(role.getId())) {
            throw new RoleNotFoundException("Role not found with id: " + role.getId());
        }

        userRegisterInfo.setRole(role);
        userRegisterInfo.setPassword(UserUseCase.encryptPassword(userRegisterInfo.getPassword(), passwordEncoder));

        return userMapper.userEntityToUserResponseDto(userRepository.saveUser(userRegisterInfo));

    }
}
