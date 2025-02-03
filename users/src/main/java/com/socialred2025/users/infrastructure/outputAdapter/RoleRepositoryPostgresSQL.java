package com.socialred2025.users.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.socialred2025.users.domain.Role;
import com.socialred2025.users.infrastructure.outputPort.IRoleRepository;

/**
 * The RoleRepositoryPostgresSQL class implements the IRoleRepository interface
 * to interact with a
 * PostgreSQL database for CRUD operations on Role entities.
 */
@Component
public class RoleRepositoryPostgresSQL implements IRoleRepository {

    private final IRoleCrudRepositoryPostgresSQL crudRepositoryPostgresSQL;

    public RoleRepositoryPostgresSQL(IRoleCrudRepositoryPostgresSQL crudRepositoryPostgresSQL) {
        this.crudRepositoryPostgresSQL = crudRepositoryPostgresSQL;
    }

    @Override
    public Role saveRole(Role role) {
        return crudRepositoryPostgresSQL.save(role);
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return crudRepositoryPostgresSQL.findById(id);
    }

    @Override
    public void deleteRoleById(Long id) {
        crudRepositoryPostgresSQL.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return crudRepositoryPostgresSQL.existsById(id);
    }

    @Override
    public Role getReferenceById(Long id) {
        return crudRepositoryPostgresSQL.getReferenceById(id);
    }

}
