package com.mobilecompany.dao.api;

import com.mobilecompany.entities.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    User read(Integer id);

    void update(Integer id);

    void delete(Integer id);

    List<User> findAllUsers();

    User getByEmail(String email);
}
