package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.dao.api.UsersDao;
import com.mobilecompany.entities.Roles;
import com.mobilecompany.entities.Users;
import com.mobilecompany.services.api.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{
    private UsersDao usersDao;
    private ModelMapper mapper;
    private RoleDao roleDao;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao, RoleDao roleDao) {
        this.usersDao = usersDao;
        this.roleDao = roleDao;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public Users getEntity(Integer id) {
        return usersDao.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> getAllUsers() {
        return usersDao.findAllUsers();
    }

    @Override
    @Transactional
    public void createUser(Users user) {
        Roles role = roleDao.getRoleByName("ROLE_USER");
        user.setRoles(role);
        usersDao.create(user);
    }

    @Override
    @Transactional
    public Users findByEmail(String email) {
        return usersDao.getByEmail(email);
    }

    @Override
    @Transactional
    public void update(Users user) {
        usersDao.update(user.getId());
    }
}
