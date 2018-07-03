package com.mobilecompany.services.api;

import com.mobilecompany.dto.UserDto;

public interface UsersService {
    UserDto getEntity(Integer id);
    String getAllUsers();
}
