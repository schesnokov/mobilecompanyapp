package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Users;

import java.util.List;

public interface UsersDao {
    void create(Users users);
    Users read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Users> findAllUsers();
    Users getByEmail(String email);
}
