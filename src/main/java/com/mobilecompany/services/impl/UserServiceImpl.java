package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.dao.api.UserDao;
import com.mobilecompany.dto.RoleDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.User;
import com.mobilecompany.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private ModelMapper mapper;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new User service.
     *
     * @param userDao the user dao
     * @param roleDao the role dao
     */
    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        mapper = new ModelMapper();
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Integer id) {
        LOGGER.info("Getting userDto from Entity with id {}", id);
        return mapper.map(userDao.read(id), UserDto.class);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        LOGGER.info("Getting list with userDtos with all users");
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userDao.findAllUsers()) {
            userDtoList.add(mapper.map(user, UserDto.class));
        }
        return userDtoList;
    }

    /**
     * Create user.
     *
     * @param user the user
     */
    @Override
    @Transactional
    public void createUser(UserDto user) {
        LOGGER.info("Passing userDto to DAO for creation of new user");
        RoleDto role = mapper.map(roleDao.getRoleByName("ROLE_USER"), RoleDto.class);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(mapper.map(user, User.class));
    }

    /**
     * Find by email user dto.
     *
     * @param email the email
     * @return the user dto
     */
    @Override
    @Transactional
    public UserDto findByEmail(String email) {
        LOGGER.info("Passing email {} to DAO for finding user", email);
        return mapper.map(userDao.getByEmail(email), UserDto.class);
    }

    /**
     * Update.
     *
     * @param user the user
     */
    @Override
    @Transactional
    public void update(UserDto user) {
        LOGGER.info("Passing userDto to DAO for updating user {}", user);
        User updatedUser = mapper.map(user, User.class);
        userDao.update(updatedUser.getId());
    }

}
