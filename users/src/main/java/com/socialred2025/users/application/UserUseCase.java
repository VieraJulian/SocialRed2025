package com.socialred2025.users.application;

import java.io.IOException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socialred2025.users.application.dto.UserCreateRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.dto.UserUpdateRequestDTO;
import com.socialred2025.users.application.exception.EmailAlreadyExistsException;
import com.socialred2025.users.application.exception.IncorrectPasswordException;
import com.socialred2025.users.application.exception.RoleNotFoundException;
import com.socialred2025.users.application.exception.UserErrorException;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.exception.UsernameAlreadyExistsException;
import com.socialred2025.users.application.mapper.IUserMapper;
import com.socialred2025.users.application.utils.IImageUpdateUtils;
import com.socialred2025.users.application.utils.IUserUpdateUtils;
import com.socialred2025.users.domain.Role;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputPort.IUserInputPort;
import com.socialred2025.users.infrastructure.outputPort.IRoleRepository;
import com.socialred2025.users.infrastructure.outputPort.IUserRepository;

/**
 * The `UserUseCase` class in Java implements methods for creating, finding,
 * updating, and deleting
 * user entities.
 */
@Service
public class UserUseCase implements IUserInputPort {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final IUserMapper iUserMapper;

    private final IUserUpdateUtils updateUtils;

    private final IImageUpdateUtils imageUpdateUtils;

    private final PasswordEncoder passwordEncoder;

    public UserUseCase(IUserRepository userRepository, IUserMapper iUserMapper, IRoleRepository roleRepository,
            IUserUpdateUtils updateUtils, IImageUpdateUtils imageUpdateUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.iUserMapper = iUserMapper;
        this.updateUtils = updateUtils;
        this.imageUpdateUtils = imageUpdateUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO createRequestDto) throws RoleNotFoundException {
        UserEntity userCreateInfo = iUserMapper.userCreateRequestDtoToUserEntity(createRequestDto);

        Long roleId = createRequestDto.getRoleId();

        if (!roleRepository.existsById(roleId)) {
            throw new RoleNotFoundException("Role not found with id: " + roleId);
        }

        Role role = roleRepository.getReferenceById(roleId);

        userCreateInfo.setPassword(encryptPassword(createRequestDto.getPassword(), passwordEncoder));
        userCreateInfo.setRole(role);

        return iUserMapper.userEntityToUserResponseDto(userRepository.saveUser(userCreateInfo));
    }

    @Override
    public UserResponseDTO findUserById(Long id) throws UserNotFoundException {
        UserEntity userDB = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return iUserMapper.userEntityToUserResponseDto(userDB);
    }

    @Override
    public String deleteUser(Long id) throws UserNotFoundException {

        if (!userRepository.existsById(id))
            throw new UserNotFoundException("User not found with id: " + id);

        if (userRepository.isEnabled(id)) {
            userRepository.deleteUserById(id);

            return "User deleted successfuly";
        } else {
            return "User is already disabled";
        }
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO updateRequestDTO, MultipartFile file)
            throws UserNotFoundException, IOException, UsernameAlreadyExistsException, EmailAlreadyExistsException,
            IncorrectPasswordException, UserErrorException {

        UserEntity userDB = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        updateUtils.updateUsername(userDB, updateRequestDTO.getNewUsername());
        updateUtils.updateEmail(userDB, updateRequestDTO.getNewEmail());
        updateUtils.updatePassword(userDB, updateRequestDTO.getNewPassword(), updateRequestDTO.getOldPassword());
        imageUpdateUtils.updateImage(userDB, file);

        UserEntity userUpdated = userRepository.saveUser(userDB);

        return iUserMapper.userEntityToUserResponseDto(userUpdated);

    }

    public static String encryptPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }

    public static boolean matchPassword(String rawPassword, String encodedPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
