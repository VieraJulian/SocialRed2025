package com.socialred2025.users.infrastructure.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialred2025.users.domain.Role;

// This code snippet is defining an interface named `IRoleCrudRepositoryPostgresSQL` that extends the
// `JpaRepository` interface provided by Spring Data JPA.
@Repository
public interface IRoleCrudRepositoryPostgresSQL extends JpaRepository<Role, Long> {

    boolean existsById(Long id);

}
