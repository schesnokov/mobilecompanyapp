package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.dao.api.UserDao;
import com.mobilecompany.entities.Role;
import com.mobilecompany.entities.User;
import com.mobilecompany.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User getUser(Integer id) {
        return userDao.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        Role role = roleDao.getRoleByName("ROLE_USER");
        user.setRole(role);
        userDao.create(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user.getId());
    }
}
