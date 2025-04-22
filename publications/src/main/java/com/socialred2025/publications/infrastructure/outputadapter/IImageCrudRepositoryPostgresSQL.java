package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This code snippet is defining an interface named `IImageCrudRepositoryPostgresSQL` that extends the
// `JpaRepository` interface provided by Spring Data JPA.
@Repository
public interface IImageCrudRepositoryPostgresSQL extends JpaRepository<Image, Long> {

}
