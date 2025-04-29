package com.socialred2025.publications.application.exception;

public class CommentLimitExceededException extends Exception {

    public CommentLimitExceededException(String msg) {
        super(msg);
    }
}
