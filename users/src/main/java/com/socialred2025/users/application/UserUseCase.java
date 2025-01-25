package com.socialred2025.users.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.socialred2025.users.application.dto.UserCreateRequestDto;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.exception.RoleNotFoundException;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.mapper.IUserMapper;
import com.socialred2025.users.domain.Role;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputPort.IUserInputPort;
import com.socialred2025.users.infrastructure.outputPort.IRoleRepository;
import com.socialred2025.users.infrastructure.outputPort.IUserRepository;

@Service
public class UserUseCase implements IUserInputPort {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final IUserMapper iUserMapper;

    public UserUseCase(IUserRepository userRepository, IUserMapper iUserMapper, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.iUserMapper = iUserMapper;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDto createRequestDto) throws RoleNotFoundException {
        UserEntity userCreateInfo = iUserMapper.userCreateRequestDtoToUserEntity(createRequestDto);

        Optional<Role> role = roleRepository.findRoleById(createRequestDto.getRoleId());

        if (role.isPresent()) {
            userCreateInfo.setRole(role.get());

        } else {
            throw new RoleNotFoundException("Role not found with id: " + createRequestDto.getRoleId());
        }

        return iUserMapper.userEntityToUserResponseDto(userRepository.saveUser(userCreateInfo));
    }

    @Override
    public UserResponseDTO findUserById(Long id) throws UserNotFoundException {
        Optional<UserEntity> userDB = userRepository.findUserById(id);

        if (userDB.isPresent()) {
            return iUserMapper.userEntityToUserResponseDto(userDB.get());
        }

        throw new UserNotFoundException("User not found with id: " + id);
    }

    @Override
    public String deleteUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> userDB = userRepository.findUserById(id);

        if (userDB.isPresent()) {
            userRepository.deleteUserById(id);
            return "User deleted successfuly";
        }

        throw new UserNotFoundException("User not found with id: " + id);
    }
}
