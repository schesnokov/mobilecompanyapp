package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Role;

/**
 * The interface Role dao.
 */
public interface RoleDao {
    /**
     * Gets role by name.
     *
     * @param roleName the role name
     * @return the role by name
     */
    Role getRoleByName(String roleName);

    /**
     * Read role.
     *
     * @param id the id
     * @return the role
     */
    Role read(Integer id);
}
