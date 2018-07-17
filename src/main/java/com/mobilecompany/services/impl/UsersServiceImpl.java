package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.dao.api.UsersDao;
import com.mobilecompany.dto.UserDto;
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
    public UserDto getEntity(Integer id) {
        Users usersEntity = usersDao.read(id);
        return mapper.map(usersEntity, UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllUsers() {
        List<Users> users = usersDao.findAllUsers();
        String result = "";
        for (Users user : users) {
            if (user.getRoles() != null) {
                result = result + " " + user + "\n";
            } else {
                result = result + " " + user.getFirstName() + " " + user.getSecondName()
                        + " user have no role" + "\n";
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void createUser(Users user) {
        Roles role = roleDao.getRoleByName("user");
        user.setRoles(role);
        usersDao.create(user);
    }

    @Override
    @Transactional
    public Users findByEmail(String email) {
        return usersDao.getByEmail(email);
    }
}
