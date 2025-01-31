package com.socialred2025.users.infrastructure.inputPort;

import java.io.IOException;

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

public interface IUserInputPort {

    UserResponseDTO createUser(UserCreateRequestDTO createRequestDto) throws RoleNotFoundException;

    UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO updateRequestDTO, MultipartFile file)
            throws UserNotFoundException, IOException, UsernameAlreadyExistsException, EmailAlreadyExistsException,
            IncorrectPasswordException, UserErrorException;

    UserResponseDTO findUserById(Long id) throws UserNotFoundException;

    String deleteUser(Long id) throws UserNotFoundException;

}
