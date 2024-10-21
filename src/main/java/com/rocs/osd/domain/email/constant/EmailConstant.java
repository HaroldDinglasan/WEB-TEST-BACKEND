package com.rocs.osd.domain.email.constant;

/**
 * Constants used for email configuration.
 */
public class EmailConstant {

    /**
     * This protocol is used for sending email.
     * Simple Mail Transfer Protocol ensure the email communication
     * that is encrypted from the sender to the email server.
     */
    public static final String SIMPLE_MAIL_TRANSFER_PROTOCOL = "smtps";

    /**
     * Email address used as the sender's address.
     */
    public static final String USERNAME = "rogationist.computing.society@gmail.com";

    /**
     * Password used for authenticating the sender's email account.
     */
    public static final String PASSWORD = "mpeo apom ojqd nqwr";

    /**
     * This is the email address that will appear as the "From" address in sent emails.
     */
    public static final String FROM_EMAIL = "rogationist.computing.society@gmail.com";

    /**
     * This constant will be used as the subject of the email welcoming users.
     */
    public static final String EMAIL_SUBJECT = "Welcome to Rogationist Computer Society: Your New Password";

    /**
     * Simple Mail Transfer Protocol server address for Gmail which will handle sending emails.
     */
    public static final String GMAIL_SMTP_SERVER = "smtp.gmail.com";

    /**
     * Simple Mail Transfer Protocol host.
     * This specifies the email server host to use.
     */
    public static final String SMTP_HOST = "mail.smtp.host";

    /**
     * Simple Mail Transfer Protocol authentication.
     * This setting ensures that the SMTP server requires authentication (username and password).
     */
    public static final String SMTP_AUTH = "mail.smtp.auth";

    /**
     * Specifying the Simple Mail Transfer Protocol port.
     * This constant defines the port number to connect to for sending emails
     */
    public static final String SMTP_PORT = "mail.smtp.port";

    /**
     * Default port for Simple Mail Transfer Protocol.
     */
    public static final int DEFAULT_PORT = 465;

    /**
     * Enabling StartTLS in Simple Mail Transfer Protocol.
     * This setting ensures that the email communication will use TLS encryption if the server supports it.
     */
    public static final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

    /**
     * Requiring StartTLS in Simple Mail Transfer Protocol.
     * This setting forces the connection to use TLS encryption to secure the communication.
     */
    public static final String SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
}
