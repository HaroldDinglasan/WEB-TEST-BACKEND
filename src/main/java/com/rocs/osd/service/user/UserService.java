package com.rocs.osd.service.user;


import com.rocs.osd.domain.register.Register;
import com.rocs.osd.domain.user.User;
import com.rocs.osd.exception.domain.*;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Service interface for managing user operations, providing methods for registration,
 * password reset, OTP verification, and username retrieval.
 */
public interface UserService {
    /**
     * Register a new user with the given details.
     *
     * @param register the registration details including user information
     * @return the registered user with associated details
     * @throws UsernameNotFoundException if the username cannot be found
     * @throws UsernameExistsException if the username already exists
     * @throws EmailExistsException if the email is already in use
     * @throws MessagingException if there is an issue with email sending
     * @throws PersonExistsException if the person already exists
     * @throws UserNotFoundException if the user is not found during registration
     */
    Register register(Register register) throws UsernameNotFoundException, UsernameExistsException, EmailExistsException, MessagingException, PersonExistsException, UserNotFoundException;
    /**
     * Initiates a password reset process by sending an OTP to the user's registered email.
     *
     * @param user the user requesting the password reset
     * @return the user with OTP set for verification
     * @throws UsernameNotFoundException if the username is not found
     * @throws MessagingException if there is an issue with email sending
     */
    User forgotPassword(User user) throws UsernameNotFoundException, MessagingException;

    /**
     * Retrieves all users.
     *
     * @return list of all users
     */
    List<User> getUsers();
    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return the user with the specified username
     */
    User findUserByUsername(String username);
    /**
     * Verifies the OTP for a password reset and updates the password if successful.
     *
     * @param newUser the user with the OTP and new password to set
     * @return the updated user
     * @throws PersonExistsException if there is a conflict with the existing data
     * @throws OtpExistsException if the OTP is incorrect or expired
     */
    User verifyOtpForgotPassword(User newUser) throws PersonExistsException, OtpExistsException;
    /**
     * Verifies an OTP to unlock a user account.
     *
     * @param username the username of the user
     * @param otp the OTP code for verification
     * @return OTP is valid
     */
    boolean verifyOtp(String username, String otp);
    /**
     * Initiates a username retrieval process by sending an OTP to the user's registered email.
     *
     * @param email the email associated with the user's account
     * @return the user with OTP set for username retrieval
     * @throws UsernameNotFoundException if the email is not found
     * @throws MessagingException if there is an issue with email sending
     */
    User forgotUsername(String email) throws UsernameNotFoundException, MessagingException;
    /**
     * Verifies the OTP for a username reset and updates the username if successful.
     *
     * @param userRequest the user with OTP and new username to set
     * @return the updated user
     * @throws OtpExistsException if the OTP is incorrect or expired
     * @throws UsernameExistsException if the new username is already in use
     */
    User verifyOtpForgotUsername(User userRequest) throws OtpExistsException, UsernameExistsException;
}
