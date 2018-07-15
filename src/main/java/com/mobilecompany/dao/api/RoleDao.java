package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Roles;

import java.util.List;

public interface RoleDao {
    Roles getRoleByName(String roleName);
    void create(Roles role);
    Roles read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Roles> findAllTariffs();
}
