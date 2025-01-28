package com.socialred2025.users.application;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socialred2025.users.application.dto.UserCreateRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.application.dto.UserUpdateRequestDTO;
import com.socialred2025.users.application.exception.EmailAlreadyExistsException;
import com.socialred2025.users.application.exception.IncorrectPasswordException;
import com.socialred2025.users.application.exception.RoleNotFoundException;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.exception.UsernameAlreadyExistsException;
import com.socialred2025.users.application.mapper.IUserMapper;
import com.socialred2025.users.domain.Image;
import com.socialred2025.users.domain.Role;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputPort.IUserInputPort;
import com.socialred2025.users.infrastructure.outputPort.IImageRepository;
import com.socialred2025.users.infrastructure.outputPort.IRoleRepository;
import com.socialred2025.users.infrastructure.outputPort.IUserRepository;
import com.socialred2025.users.infrastructure.utils.ImageUtils;

@Service
public class UserUseCase implements IUserInputPort {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final IImageRepository imageRepository;

    private final IUserMapper iUserMapper;

    private final ImageUtils imageUtils;

    public UserUseCase(IUserRepository userRepository, IUserMapper iUserMapper, IRoleRepository roleRepository,
            IImageRepository imageRepository,
            ImageUtils imageUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
        this.iUserMapper = iUserMapper;
        this.imageUtils = imageUtils;
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO createRequestDto) throws RoleNotFoundException {
        UserEntity userCreateInfo = iUserMapper.userCreateRequestDtoToUserEntity(createRequestDto);

        Role role = roleRepository.findRoleById(createRequestDto.getRoleId()).orElseThrow(
                () -> new RoleNotFoundException("Role not found with id: " + createRequestDto.getRoleId()));

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
        UserEntity userDB = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        if (!userDB.isEnabled()) {
            return "User is already disabled";
        }

        userRepository.deleteUserById(id);

        UserEntity updatedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        if (!updatedUser.isEnabled()) {
            return "User deleted successfuly";
        } else {
            return "Failed to disable the user";
        }

    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO updateRequestDTO, MultipartFile file)
            throws UserNotFoundException, IOException, UsernameAlreadyExistsException, EmailAlreadyExistsException,
            IncorrectPasswordException {

        UserEntity userDB = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        String currentPassword = userDB.getPassword();
        String newUsername = updateRequestDTO.getNewUsername();
        String newEmail = updateRequestDTO.getNewEmail();
        String oldPassword = updateRequestDTO.getOldPassword();
        String newPassword = updateRequestDTO.getNewPassword();
        Image userImage = userDB.getImage();

        if (newUsername != null && !newUsername.isEmpty()) {
            if (userRepository.existsByUsername(newUsername)) {
                throw new UsernameAlreadyExistsException("The username " + newUsername + " is already in use");
            }
            userDB.setUsername(newUsername);
        }

        if (newEmail != null) {
            if (userRepository.existsByEmail(newEmail)) {
                throw new EmailAlreadyExistsException("The email " + newEmail + " is already in use.");
            }
            userDB.setEmail(newEmail);
        }

        if (newPassword != null && oldPassword != null && !oldPassword.equals(currentPassword)) {
            throw new IncorrectPasswordException("The provided password is incorrect.");
        }

        if (newPassword != null) {
            userDB.setPassword(newPassword);
        }

        if (!file.isEmpty()) {
            Image newImage = imageUtils.fileUpload(file);

            if (userImage != null) {
                Optional<Image> imageDB = imageRepository.findImageById(userImage.getId());

                if (imageDB.isPresent()) {
                    imageDB.get().setImageUrl(newImage.getImageUrl());

                    Image saveUserImage = imageRepository.saveImage(imageDB.get());

                    userDB.setImage(saveUserImage);
                }
            } else {
                Image newUserImage = Image.builder()
                        .imageUrl(newImage.getImageUrl())
                        .build();

                Image saveUserImage = imageRepository.saveImage(newUserImage);

                userDB.setImage(saveUserImage);
            }

        }

        UserEntity userUpdated = userRepository.saveUser(userDB);

        return iUserMapper.userEntityToUserResponseDto(userUpdated);

    }
}
