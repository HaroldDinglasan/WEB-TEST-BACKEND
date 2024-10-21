package com.rocs.osd.service.otp.attempt;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
 * Manages OTP (One-Time Password) attempts and helps prevent misuse by limiting
 * the number of allowed OTP attempts
 */
@Service
public class OtpAttemptService {
    private static final Logger logger = LoggerFactory.getLogger(OtpAttemptService.class);
    /**
     * Maximum number of OTP attempts allowed before further action is taken.
     */
    public static final int MAX_NUMBER_OF_ATTEMPTS = 5;
    /**
     * Number of attempts added per failed OTP attempt.
     */
    public static final int ATTEMPT_INCREMENTS = 1;
    private LoadingCache<String, Integer> otpAttemptCache;

    /**
     * Initializes the OTP attempt with a maximum size of 100 entries,
     * where each entry expires 15 minutes after the last attempt.
     */
    public OtpAttemptService() {
        super();
        otpAttemptCache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }
    /**
     * Evicts a user from the OTP attempt
     *
     * @param username the username to be evicted from the cache
     */
    public void evictUserFromLoginAttemptCache(String username) {
        otpAttemptCache.invalidate(username);
    }
    /**
     * Increments the OTP attempt count for the specified user.
     *
     * @param username the username whose OTP attempts will be incremented
     */
    public void addUserToLoginAttemptCache(String username) {
        int attempts = 0;
        try {
            attempts = ATTEMPT_INCREMENTS + otpAttemptCache.get(username);
        } catch (ExecutionException e) {
            logger.error("Failed to retrieve OTP attempt count for user: {}", username, e);
        }
        otpAttemptCache.put(username, attempts);
    }
    /**
     * Checks if the specified user has exceeded the maximum number of OTP attempts.
     *
     * @param username username to check
     * @return true if the user has reached or exceeded the maximum allowed OTP attempts, false otherwise
     */
    public boolean hasExceededMaxAttempts(String username) {
        try {
            return otpAttemptCache.get(username) >= MAX_NUMBER_OF_ATTEMPTS;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
