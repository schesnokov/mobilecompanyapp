package com.mobilecompany.services.api;

import com.mobilecompany.dto.UserDto;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    UserDto getUser(Integer id);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserDto> getAllUsers();

    /**
     * Create user.
     *
     * @param user the user
     */
    void createUser(UserDto user);

    /**
     * Find by email user dto.
     *
     * @param email the email
     * @return the user dto
     */
    UserDto findByEmail(String email);

    /**
     * Update.
     *
     * @param user the user
     */
    void update(UserDto user);
}
