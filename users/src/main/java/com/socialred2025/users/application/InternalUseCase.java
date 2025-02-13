package com.socialred2025.users.application;

import com.socialred2025.users.application.dto.InternalUserResponseDTO;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.mapper.IUserMapper;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputPort.IInternalUserInputPort;
import com.socialred2025.users.infrastructure.outputPort.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class InternalUseCase implements IInternalUserInputPort {

    private final IUserRepository userRepository;

    private final IUserMapper userMapper;

    public InternalUseCase(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public InternalUserResponseDTO getUser(String username) throws UserNotFoundException {
        UserEntity userDB = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        return userMapper.userEntityToInternalUserResponseDTO(userDB);
    }
}
