package com.socialred2025.users.infrastructure.outputPort;

import java.util.Optional;

import com.socialred2025.users.domain.Role;

public interface IRoleRepository {

    Role saveRole(Role role);
    Optional<Role> findRoleById(Long id);
    public void deleteRoleById(Long id);

}
