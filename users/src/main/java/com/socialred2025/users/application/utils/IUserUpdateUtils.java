package com.socialred2025.users.application.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.socialred2025.users.application.exception.EmailAlreadyExistsException;
import com.socialred2025.users.application.exception.IncorrectPasswordException;
import com.socialred2025.users.application.exception.UserErrorException;
import com.socialred2025.users.application.exception.UsernameAlreadyExistsException;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.outputPort.IUserRepository;

@Component
public class IUserUpdateUtils {

    private final IUserRepository userRepository;

    public IUserUpdateUtils(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUsername(UserEntity user, String username)
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

    public void updateEmail(UserEntity user, String email) throws EmailAlreadyExistsException, UserErrorException {
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

    public void updatePassword(UserEntity user, String newPassword, String oldPassword)
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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
