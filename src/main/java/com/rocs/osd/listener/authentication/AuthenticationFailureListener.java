package com.rocs.osd.listener.authentication;

import com.rocs.osd.service.login.attempt.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * Listener component that handles authentication failure events caused by bad credentials.
 * This track failed login attempts and potentially prevent brute-force attacks by limiting
 * the number of failed attempts.
 */
@Component
public class AuthenticationFailureListener {

    /**
     * Service responsible for managing login attempts and limiting access based on failed login.
     */
    public LoginAttemptService loginAttemptService;
    /**
     *This is to track failed login attempts and take action based on the number of unsuccessful attempts.
     *
     * @param loginAttemptService service responsible for handling login attempts
     */
    @Autowired
    public AuthenticationFailureListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }
    /**
     * Listens for authentication failures caused by bad credentials and adds the user's
     *
     * @param event event triggered on authentication failure
     */
    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof String) {
            String username = (String) event.getAuthentication().getPrincipal();
            loginAttemptService.addUserToLoginAttemptCache(username);
        }

    }

}
