package com.rocs.osd.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.rocs.osd.domain.http.response.HttpResponse;
import com.rocs.osd.exception.domain.*;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

import static com.rocs.osd.exception.constant.ExceptionConstant.*;
import static org.springframework.http.HttpStatus.*;
/**
 * This class handles all the exceptions for the application.
 */
@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    /**
     * Handles DisabledAccountException when the user's account is disabled.
     * @return ResponseEntity status and account disabled error message.
     *
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }
    /**
     * Handles BadCredentialsException when the user provides incorrect login credentials.
     * @return ResponseEntity status and incorrect credentials error message.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }
    /**
     * Handles AccessDeniedException when the user tries to access a resource without permission.
     * @return ResponseEntity status and access denied error message.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }
    /**
     * Handles LockedException when the user's account is locked.
     * @return ResponseEntity status and account locked error message.
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }
    /**
     * Handles TokenExpiredException when the user's JWT token has expired.
     *
     * @param exception TokenExpiredException thrown when the token expires.
     * @return ResponseEntity status and token expired error message.
     *
     */
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }
    /**
     * Handles EmailExistsException when the email address is already registered.
     *
     * @param exception EmailExistsException thrown when the email is already taken.
     * @return ResponseEntity status and email exists error message.
     */
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistsException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    /**
     * Handles UsernameExistsException when the username is already registered.
     *
     * @param exception UsernameExistsException thrown when the username is already taken.
     * @return ResponseEntity status and username exists error message.
     */
    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<HttpResponse> usernameExistException(UsernameExistsException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    /**
     * Handles PersonExistsException when the person already exists in the system.
     *
     * @param exception PersonExistsException thrown when a duplicate person is found.
     * @return ResponseEntity status and person exists error message.
     */
    @ExceptionHandler(PersonExistsException.class)
    public ResponseEntity<HttpResponse> personExistsException(PersonExistsException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    /**
     * Handles OtpExistsException when the OTP already exists.
     *
     * @param exception the OtpExistsException thrown when an OTP is already in use.
     * @return ResponseEntity status and OTP exists error message.
    */
    @ExceptionHandler(OtpExistsException.class)
    public ResponseEntity<HttpResponse> otpExistsException(OtpExistsException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    /**
     * Handles UsernameNotFoundException when the username cannot be found.
     *
     * @param exception UsernameNotFoundException thrown when the username is not found.
     * @return ResponseEntity and username not found error message.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponse> usernameNotFoundException(UsernameNotFoundException exception) {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }
    /**
     * Handles EmailNotFoundException when the email cannot be found.
     *
     * @param exception the EmailNotFoundException thrown when the email is not found.
     * @return ResponseEntity and email not found error message.
     */
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    /**
     * Handles UserNotFoundException when the user cannot be found.
     *
     * @param exception the UserNotFoundException thrown when the user is not found.
     * @return ResponseEntity status and user not found error message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    /**
     * Handles any other uncaught exceptions and logs them.
     *
     * @param exception the uncaught Exception.
     * @return ResponseEntity and internal server error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }
    /**
     * Handles NoResultException when no result is found in the database.
     *
     * @param exception the NoResultException thrown when no entity is found.
     * @return ResponseEntity status and not found error message.
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }
    /**
     * Handles IOException during file processing.
     *
     * @param exception the IOException thrown during file operations.
     * @return ResponseEntity status and file processing error message.
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }
    /**
     * Handles NoHandlerFoundException when the requested endpoint is not found.
     *
     * @param e the NoHandlerFoundException thrown when no endpoint is mapped.
     * @return ResponseEntity status and endpoint not found error message.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
        return createHttpResponse(BAD_REQUEST, "There is no mapping for this URL");
    }
    /**
     * Creates a standardized HTTP response for exceptions.
     *
     * @param httpStatus HTTP status to be returned.
     * @param message message to be included in the response.
     */
    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }
    /**
     * Handles requests to undefined endpoints.
     * @return ResponseEntity and endpoint not found error message.
     */
    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
    }
}
