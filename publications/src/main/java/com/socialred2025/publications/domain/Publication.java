package com.socialred2025.publications.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE publications SET enabled = false, deleted_at = NOW() WHERE id = ?")
@Entity(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long userId;

    @Size(max = 300)
    @NotBlank
    @Column(length = 300, nullable = false)
    private String description;

    private int likesCount = 0;

    private boolean edited = false;

    private boolean enabled = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToOne
    @JoinColumn(name = "imageId")
    private Image image;

    @OneToMany
    @JoinColumn(name = "publicationId")
    private Set<Like> likes = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "publicationId")
    private Set<Comment> comments = new HashSet<>();

}
