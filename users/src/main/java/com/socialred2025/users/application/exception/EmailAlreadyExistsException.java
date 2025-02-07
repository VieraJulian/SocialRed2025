package com.socialred2025.users.application.exception;

/**
 * The class EmailAlreadyExistsException extends Exception and represents an
 * exception for when an
 * email already exists.
 */
public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }

}
