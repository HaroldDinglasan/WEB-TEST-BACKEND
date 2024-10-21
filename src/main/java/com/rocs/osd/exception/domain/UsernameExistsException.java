package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a username already exists in the system.
 */
public class UsernameExistsException extends Exception {

    /**
     * Constructs a new UsernameExistsException with a message.
     *
     * @param message message explaining the reason for the exception
     */
    public UsernameExistsException(String message) {
        super(message);
    }
}
