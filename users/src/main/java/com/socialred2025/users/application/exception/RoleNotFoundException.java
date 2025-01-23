package com.socialred2025.users.application.exception;

/**
 * The RoleNotFoundException class is a custom exception in Java that is used to handle situations
 * where a specific role is not found.
 */
public class RoleNotFoundException extends Exception {

    public RoleNotFoundException(String msg) {
        super(msg);
    }

}
