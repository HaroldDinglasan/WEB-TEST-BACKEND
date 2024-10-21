package com.rocs.osd.utils.security.constant;
/**
 * Contains predefined authorities for different roles
 */
public class Authority {
    /** Authorities for students. */
    public static final String[] STUDENT_AUTHORITIES = {"user:read"};
    /** Authorities for guests. */
    public static final String[] GUEST_AUTHORITIES = {"user:read"};
    /** Authorities for employees. */
    public static final String[] EMPLOYEE_AUTHORITIES = {"user:read", "user:update"};
    /** Authorities for administrators. */
    public static final String[] ADMIN_AUTHORITIES = {"user:read", "user:create", "user:update"};
    /** Authorities for super administrators. */
    public static final String[] SUPER_ADMIN_AUTHORITIES = {"user:read", "user:create", "user:update", "user:delete"};
}
