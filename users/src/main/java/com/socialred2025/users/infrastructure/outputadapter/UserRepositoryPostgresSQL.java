package com.socialred2025.users.infrastructure.outputadapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.outputport.IUserRepository;

/**
 * The UserRepositoryPostgresSQL class implements IUserRepository interface
 * methods using a CRUD
 * repository for PostgreSQL database operations.
 */
@Component
public class UserRepositoryPostgresSQL implements IUserRepository {

    private final IUserCrudRepositoryPostgresSQL crudRepositoryPostgresSQL;

    public UserRepositoryPostgresSQL(IUserCrudRepositoryPostgresSQL crudRepositoryPostgresSQL) {
        this.crudRepositoryPostgresSQL = crudRepositoryPostgresSQL;
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return crudRepositoryPostgresSQL.save(user);
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return crudRepositoryPostgresSQL.findById(id);
    }

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return crudRepositoryPostgresSQL.findByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return crudRepositoryPostgresSQL.existsByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
        crudRepositoryPostgresSQL.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return crudRepositoryPostgresSQL.existsByUsername(username);
    }

    @Override
    public boolean existsById(Long id) {
        return crudRepositoryPostgresSQL.existsById(id);
    }

    @Override
    public boolean isEnabled(Long id) {
        return crudRepositoryPostgresSQL.isEnabled(id);
    }

}
