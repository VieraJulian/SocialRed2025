package com.socialred2025.users.application.exception;

/**
 * The class `IncorrectPasswordException` extends the `Exception` class and is
 * used to represent an
 * exception for incorrect passwords.
 */
public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException(String msg) {
        super(msg);
    }

}
