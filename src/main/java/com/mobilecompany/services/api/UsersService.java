package com.mobilecompany.services.api;

import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.Users;

public interface UsersService {
    UserDto getEntity(Integer id);
    String getAllUsers();
    void createUser(Users user);
}
