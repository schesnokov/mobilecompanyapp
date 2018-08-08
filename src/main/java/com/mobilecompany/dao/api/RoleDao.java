package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Role;

public interface RoleDao {
    Role getRoleByName(String roleName);

    Role read(Integer id);
}
