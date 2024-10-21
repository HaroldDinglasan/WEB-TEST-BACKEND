package com.rocs.osd.utils.security.enumeration;

import static com.rocs.osd.utils.security.constant.Authority.*;

/**
 * Enum representing the different roles in the system and their associated authorities.
 */
public enum Role {

    /** Role for students with read access. */
    ROLE_STUDENT(STUDENT_AUTHORITIES),

    /** Role for employees with read and update access. */
    ROLE_EMPLOYEE(EMPLOYEE_AUTHORITIES),

    /** Role for guests with read access. */
    ROLE_GUEST(GUEST_AUTHORITIES),

    /** Role for administrators with read, create, and update access. */
    ROLE_ADMIN(ADMIN_AUTHORITIES),

    /** Role for super administrators with read, create, update, and delete access. */
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    /** Authorities associated with the role. */
    private final String[] authorities;

    /**
     * Constructor for the Role enum.
     *
     * @param authorities authorities associated with the role
     */
    Role(String... authorities) {
        this.authorities = authorities;
    }
    /**
     * Get the authorities associated with the role.
     *
     * @return array of authorities
     */
    public String[] getAuthorities() {
        return authorities;
    }
}
