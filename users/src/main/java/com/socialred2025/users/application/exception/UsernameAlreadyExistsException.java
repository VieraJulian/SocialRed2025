package com.socialred2025.users.application.exception;

/**
 * The class `UsernameAlreadyExistsException` extends `Exception` and represents
 * an exception for when
 * a username already exists.
 */
public class UsernameAlreadyExistsException extends Exception {

    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }

}
