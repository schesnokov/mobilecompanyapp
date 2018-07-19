package com.mobilecompany.services.api;

import com.mobilecompany.entities.Users;

import java.util.List;

public interface UsersService {
    Users getEntity(Integer id);
    List<Users> getAllUsers();
    void createUser(Users user);
    Users findByEmail(String email);
    void update(Users user);
}
