package com.socialred2025.users.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.socialred2025.users.domain.UserEntity;

// This code snippet defines a Spring Data JPA repository interface named
// `IUserCrudRepositoryPostgresSQL` that extends `JpaRepository<UserEntity, Long>`.
@Repository
public interface IUserCrudRepositoryPostgresSQL extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT u.enabled FROM UserEntity u WHERE id = :id")
    boolean isEnabled(@Param("id") Long id);
}