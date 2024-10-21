package com.rocs.osd.repository.user;

import com.rocs.osd.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An interface to the user repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username username of the user
     * @return username
     */
    User findUserByUsername(String username);

    /**
     * Checks if a user exists with username.
     *
     * @param username the username of the user
     * @return user with username
     */
    boolean existsUserByUsername(String username);

}
