package com.mobilecompany.dao.api;

import com.mobilecompany.entities.User;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Create.
     *
     * @param user the user
     */
    void create(User user);

    /**
     * Read user.
     *
     * @param id the id
     * @return the user
     */
    User read(Integer id);

    /**
     * Update.
     *
     * @param id the id
     */
    void update(Integer id);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Integer id);

    /**
     * Find all users list.
     *
     * @return the list of users
     */
    List<User> findAllUsers();

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the User
     */
    User getByEmail(String email);
}
