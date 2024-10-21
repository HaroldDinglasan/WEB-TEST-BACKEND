package com.rocs.osd.utils.security.constant;

/**
 * Contains constants used for security configurations.
 */
public class SecurityConstant {

    /** Token expiration time in milliseconds (5 days). */
    public static final long EXPIRATION_TIME = 432_000_000L;

    /** Prefix for Bearer tokens in HTTP headers. */
    public static final String TOKEN_PREFIX = "Bearer ";

    /** Header name for JWT tokens in HTTP requests. */
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";

    /** Error message when a token cannot be verified. */
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";

    /** Application name. */
    public static final String ROCS = "OsdRecordsMgtWebApplication";

    /** Application description. */
    public static final String ROCS_ADMINISTRATION = "Prefect System Self Service";

    /** Key for storing authorities in JWT claims. */
    public static final String AUTHORITIES = "authorities";

    /** Error message for unauthorized access (login required). */
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";

    /** Error message for access denied (insufficient permissions). */
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";

    /** HTTP method for preflight requests. */
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

    /** Publicly accessible URLs without authentication. */
    public static final String[] PUBLIC_URLS = {
            "/user/login",
            "/user/register",
            "/user/verify-otp",
            "/user/forgot-password",
            "/user/forgot-username",
            "/user/verify-otp-forgot-username",
            "/user/verify-forgot-password",
            "guest/addGuest"
    };
}
