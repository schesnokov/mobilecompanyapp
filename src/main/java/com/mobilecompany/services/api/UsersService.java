package com.mobilecompany.services.api;

import com.mobilecompany.dto.UsersDto;

public interface UsersService {
    UsersDto getEntity(Integer id);
    String getAllUsers();
}
