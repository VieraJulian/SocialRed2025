package com.socialred2025.users.infrastructure.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialred2025.users.domain.Image;

// This code snippet is defining an interface named `IImageCrudRepositoryPostgresSQL` that extends the
// `JpaRepository` interface provided by Spring Data JPA.
@Repository
public interface IImageCrudRepositoryPostgresSQL extends JpaRepository<Image, Long> {

}
