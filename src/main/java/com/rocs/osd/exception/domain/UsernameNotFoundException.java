package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when a username is not found in the system.
 */
public class UsernameNotFoundException extends Exception {

    /**
     * Constructs a new UsernameNotFoundException
     */
    public UsernameNotFoundException() {
        super();
    }

    /**
     * Constructs a new UsernameNotFoundException with a message.
     *
     * @param message message explaining the reason for the exception
     *
     */
    public UsernameNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UsernameNotFoundException with a message
     * and cause.
     *
     * @param message message explaining the reason for the exception
     * @param cause the cause of the exception
     */
    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UsernameNotFoundException with the cause.
     *
     * @param cause cause of the exception
     */
    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }
}
