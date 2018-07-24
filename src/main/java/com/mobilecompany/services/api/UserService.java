package com.mobilecompany.services.api;

import com.mobilecompany.dto.UserDto;

import java.sql.Date;
import java.util.List;

public interface UserService {
    UserDto createNewUserDto(String firstName, String secondName, Date dateOfBirth,
                             String adress, String passport, String email, String password);
    UserDto getUser(Integer id);
    List<UserDto> getAllUsers();
    void createUser(UserDto user);
    UserDto findByEmail(String email);
    void update(UserDto user);
}
