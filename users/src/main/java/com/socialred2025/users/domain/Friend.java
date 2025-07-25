package com.socialred2025.users.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * The `Friend` class represents a friendship entity with fields for id, user, friend user, and status.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friends")
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "friend_user_id", nullable = false)
    private UserEntity userFriend;

    @Enumerated(EnumType.STRING)
    private StatusType status;


}
