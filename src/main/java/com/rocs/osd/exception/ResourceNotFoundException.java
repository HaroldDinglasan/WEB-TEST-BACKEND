package com.rocs.osd.exception;

/**
 * This exception thrown when a requested resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Creates a new ResourceNotFoundException with no message.
     */
    public ResourceNotFoundException() {
        super();
    }
    /**
     * Creates a new ResourceNotFoundException with a specified message.
     *
     * @param message detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    /**
     * Creates a new ResourceNotFoundException with a specified message and cause.
     *
     * @param message detail message
     * @param cause cause of the exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Creates a new ResourceNotFoundException with a specified cause.
     *
     * @param cause cause of the exception
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
