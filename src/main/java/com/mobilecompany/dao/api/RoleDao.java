package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName(String roleName);
    void create(Role role);
    Role read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Role> findAllTariffs();
}
