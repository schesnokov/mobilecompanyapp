package com.mobilecompany.services.api;

import com.mobilecompany.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(Integer id);
    List<UserDto> getAllUsers();
    void createUser(UserDto user);
    UserDto findByEmail(String email);
    void update(UserDto user);
}
