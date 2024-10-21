package com.rocs.osd.exception.constant;

/**
 * This class holds constant messages for exceptions in the application and
 *  provide a consistent error messages for various exception scenarios.
 */
public class ExceptionConstant {

    /** Error message indicating that the account is locked. */
    public static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";

    /** Error message indicating that the HTTP method is not allowed on a particular endpoint. */
    public static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";

    /** Error message for general internal server errors. */
    public static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";

    /** Error message indicating incorrect username or password. */
    public static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";

    /** Error message indicating that the account has been disabled. */
    public static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";

    /** Error message indicating an issue with processing a file. */
    public static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";

    /** Error message indicating insufficient permissions. */
    public static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

    /** Error path for handling errors. */
    public static final String ERROR_PATH = "/error";
}
