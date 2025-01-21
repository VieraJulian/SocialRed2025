package com.socialred2025.users.domain;

// This code snippet is defining a Java enum called `PermissionType`. Enums in Java are used to define
// a fixed set of constants. In this case, the `PermissionType` enum is defining various permissions
// that can be assigned to users in a social media application. Each constant within the enum
// represents a specific permission that a user can have, such as editing a profile, creating a post,
// commenting on posts, liking posts, following users, and so on. This enum provides a structured way
// to manage and check for permissions within the application.
public enum PermissionType {
    EDIT_PROFILE,
    CREATE_POST,
    COMMENT_ON_POSTS,
    LIKE_POSTS,
    FOLLOW_USERS,
    SEND_ACCEPT_FRIEND_REQUESTS,
    EDIT_DELETE_USER_PROFILES,
    BLOCK_SUSPEND_ACCOUNTS,
    DELETE_ANY_POSTS_COMMENTS

}
