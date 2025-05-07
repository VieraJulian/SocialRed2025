package com.socialred2025.users.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * The Follow class represents a relationship between two UserEntity instances where one user follows
 * another.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", nullable = false)
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "followed_user_id", nullable = false)
    private UserEntity followed;
}
