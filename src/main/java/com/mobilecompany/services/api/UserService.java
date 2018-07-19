package com.mobilecompany.services.api;

import com.mobilecompany.entities.User;

import java.util.List;

public interface UserService {
    User getUser(Integer id);
    List<User> getAllUsers();
    void createUser(User user);
    User findByEmail(String email);
    void update(User user);
}
