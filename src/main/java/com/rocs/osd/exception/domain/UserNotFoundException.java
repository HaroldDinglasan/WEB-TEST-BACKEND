package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a user is not found in the system.
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructs a new UserNotFoundException with a message.
     *
     * @param message message explaining the reason for the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
