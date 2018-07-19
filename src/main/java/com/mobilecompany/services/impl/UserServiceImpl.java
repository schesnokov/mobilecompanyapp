package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.dao.api.UserDao;
import com.mobilecompany.dto.RoleDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.User;
import com.mobilecompany.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ModelMapper mapper;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Integer id) {
        return mapper.map(userDao.read(id), UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: userDao.findAllUsers()) {
            userDtoList.add(mapper.map(user, UserDto.class));
        }
        return userDtoList;
    }

    @Override
    @Transactional
    public void createUser(UserDto user) {
        RoleDto role = mapper.map(roleDao.getRoleByName("ROLE_USER"), RoleDto.class);
        user.setRole(role);
        userDao.create(mapper.map(user, User.class));
    }

    @Override
    @Transactional
    public UserDto findByEmail(String email) {
        return mapper.map(userDao.getByEmail(email), UserDto.class);
    }

    @Override
    @Transactional
    public void update(UserDto user) {
        User updatedUser = mapper.map(user, User.class);
        userDao.update(updatedUser.getId());
    }
}
