package com.socialred2025.publications.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String description;
    private int likesCount;

    @OneToOne
    @JoinColumn(name = "imageId")
    private Image image;

    @OneToMany
    @JoinColumn(name = "publicationId")
    private Set<Like> likes;

    @OneToMany
    @JoinColumn(name = "publicationId")
    private Set<Comment> comments;

}
