package com.socialred2025.users.infrastructure.outputPort;

import java.util.Optional;

import com.socialred2025.users.domain.UserEntity;

public interface IUserRepository {

    UserEntity saveUser(UserEntity user);
    Optional<UserEntity> findUserById(Long id);
    Optional<UserEntity> findUserByUsername(String username);
    boolean existsByEmail(String email);
    void deleteUserById(Long id);

}
