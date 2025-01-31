package com.socialred2025.users.application;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

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

        updateUsername(userDB, updateRequestDTO.getNewUsername());
        updateEmail(userDB, updateRequestDTO.getNewEmail());
        updatePassword(userDB, updateRequestDTO.getNewPassword(), updateRequestDTO.getOldPassword());
        updateImage(userDB, file);

        UserEntity userUpdated = userRepository.saveUser(userDB);

        return iUserMapper.userEntityToUserResponseDto(userUpdated);

    }

    private void updateUsername(UserEntity user, String username)
            throws UsernameAlreadyExistsException, UserErrorException {
        if (username != null && !username.isEmpty()) {
            if (userRepository.existsByUsername(username)) {
                throw new UsernameAlreadyExistsException("The username " + username + " is already in use");
            }

            int characters = username.length();

            if (characters < 3 || characters > 50) {
                throw new UserErrorException("Username must be between 3 and 50 characters");
            }

            user.setUsername(username);
        }
    }

    private void updateEmail(UserEntity user, String email) throws EmailAlreadyExistsException, UserErrorException {
        if (email != null && !email.isEmpty()) {
            if (userRepository.existsByEmail(email)) {
                throw new EmailAlreadyExistsException("The email " + email + " is already in use.");
            }

            if (!isValidEmail(email)) {
                throw new UserErrorException("The email " + email + " is not valid.");
            }

            user.setEmail(email);
        }
    }

    private void updatePassword(UserEntity user, String newPassword, String oldPassword)
            throws IncorrectPasswordException, UserErrorException {
        if (newPassword != null && !newPassword.isEmpty()) {
            if (oldPassword == null || !user.getPassword().equals(oldPassword)) {
                throw new IncorrectPasswordException("The provided password is incorrect.");
            }

            if (newPassword.length() < 8) {
                throw new UserErrorException("The new password must have at least 8 characters.");
            }

            user.setPassword(newPassword);
        }
    }

    private void updateImage(UserEntity user, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Image newImage = imageUtils.fileUpload(file);
            Image oldImage = user.getImage();
            Image saveUserImage = null;

            if (oldImage != null) {
                Optional<Image> imageDB = imageRepository.findImageById(oldImage.getId());

                if (imageDB.isPresent()) {
                    imageDB.get().setImageUrl(newImage.getImageUrl());

                    saveUserImage = imageRepository.saveImage(imageDB.get());
                }

            } else {
                Image newUserImage = Image.builder()
                        .imageUrl(newImage.getImageUrl())
                        .build();

                saveUserImage = imageRepository.saveImage(newUserImage);
            }

            user.setImage(saveUserImage);

        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
