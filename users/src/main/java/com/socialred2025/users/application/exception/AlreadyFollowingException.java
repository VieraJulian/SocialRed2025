package com.socialred2025.users.application.exception;

/**
 * The class `IncorrectPasswordException` extends the `Exception` class and is
 * used to represent an
 * exception for incorrect passwords.
 */
public class AlreadyFollowingException extends Exception {

    public AlreadyFollowingException(String msg) {
        super(msg);
    }

}
