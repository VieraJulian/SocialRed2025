package com.socialred2025.publications.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 250, message = "Comment description cannot exceed 250 characters")
    @NotBlank(message = "Comment description cannot be blank")
    @Column(length = 250)
    private String commentDescription;

    @NotNull(message = "User ID cannot be null")
    @Column(nullable = false)
    private Long userId;
}
