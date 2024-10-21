package com.rocs.osd.service.login.attempt;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
 * This manage login attempts and prevent brute force attacks.
 */
@Service
public class LoginAttemptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAttemptService.class);

    /**
     * The maximum number of failed login attempts allowed.
     */
    public static final int MAX_NUMBER_OF_ATTEMPTS = 5;
    /**
     * The number of attempts to increment with each failed login attempt.
     */
    public static final int ATTEMPT_INCREMENTS = 1;

    private LoadingCache<String, Integer> loginAttemptCache;

    /**
     * Initializes the login attempt cache with a maximum size of 100 entries, where each entry
     * expires after 15 minutes.
     */
    public LoginAttemptService() {
        super();
        loginAttemptCache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<>() {
            @Override
            public Integer load(String key)  {
                return 0;
            }
        });
    }
    /**
     * Removes a user from the login attempt cache, effectively resetting their login attempt count.
     *
     * @param username username to remove from the cache
     */
    public void evictUserFromLoginAttemptCache(String username) {
        loginAttemptCache.invalidate(username);
    }
    /**
     * Increments the login attempt count for user.
     *
     * @param username username to increment login attempts
     */
    public void addUserToLoginAttemptCache(String username) {
        int attempts = 0;
        try {
            attempts = ATTEMPT_INCREMENTS + loginAttemptCache.get(username);
        } catch (ExecutionException e) {
            LOGGER.error("Failed to load login attempts for user: {}", username, e);
        }
        loginAttemptCache.put(username, attempts);
    }
    /**
     * Checks if a user has exceeded the maximum number
     *
     * @param username username to check
     * @return true if the user has reached or exceeded the maximum allowed login attempts
     */
    public boolean hasExceededMaxAttempts(String username) {
        try {
            return loginAttemptCache.get(username) >= MAX_NUMBER_OF_ATTEMPTS;
        } catch (ExecutionException e) {
            LOGGER.error("Failed to retrieve login attempts for user: {}", username, e);
        }
        return false;
    }
}
