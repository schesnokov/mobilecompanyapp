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

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private ModelMapper mapper;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
        mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Integer id) {
        LOGGER.info("Getting userDto from Entity with id {}", id);
        return mapper.map(userDao.read(id), UserDto.class);
    }

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

    @Override
    @Transactional
    public void createUser(UserDto user) {
        LOGGER.info("Passing userDto to DAO for creation of new user");
        RoleDto role = mapper.map(roleDao.getRoleByName("ROLE_USER"), RoleDto.class);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(mapper.map(user, User.class));
    }

    @Override
    @Transactional
    public UserDto findByEmail(String email) {
        LOGGER.info("Passing email {} to DAO for finding user", email);
        return mapper.map(userDao.getByEmail(email), UserDto.class);
    }

    @Override
    @Transactional
    public void update(UserDto user) {
        LOGGER.info("Passing userDto to DAO for updating user {}", user);
        User updatedUser = mapper.map(user, User.class);
        userDao.update(updatedUser.getId());
    }

}
