package com.socialred2025.users.application.exception;

/**
 * The class `UserNotFoundException` extends `Exception` and represents an exception for when a user is
 * not found.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String msg) {
        super(msg);
    } 

}
