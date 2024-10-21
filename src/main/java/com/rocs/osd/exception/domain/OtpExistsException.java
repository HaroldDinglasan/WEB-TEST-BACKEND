package com.rocs.osd.exception.domain;

/**
 * This exception is thrown when an OTP (One-Time Password) already exists in the system.
 */
public class OtpExistsException extends Exception {

    /**
     * Constructs a new OtpExistsException with a message.
     *
     * @param message message explaining the reason for the exception
     */
    public OtpExistsException(String message) {
        super(message);
    }
}
