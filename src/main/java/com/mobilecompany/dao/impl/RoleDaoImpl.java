package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.entities.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Role role) {

    }

    @Override
    public Role read(Integer id) {
        return null;
    }

    @Override
    public void update(Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Role> findAllTariffs() {
        return null;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.createQuery("from Role as role where role.name=:roleName", Role.class).
                setParameter("roleName", roleName).getSingleResult();
    }
}
