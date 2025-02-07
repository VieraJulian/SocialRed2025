package com.socialred2025.users.application.exception;

/**
 * The class `UserErrorException` extends `Exception` and represents an
 * exception that is specific to
 * user errors.
 */
public class UserErrorException extends Exception {

    public UserErrorException(String msg) {
        super(msg);
    }

}
