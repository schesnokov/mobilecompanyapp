package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.UsersDao;
import com.mobilecompany.dto.UsersDto;
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

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public UsersDto getEntity(Integer id) {
        Users usersEntity = usersDao.read(id);
        return mapper.map(usersEntity, UsersDto.class);
    }

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
}
