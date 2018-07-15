package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.RoleDao;
import com.mobilecompany.entities.Roles;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RolesDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Roles role) {

    }

    @Override
    public Roles read(Integer id) {
        return null;
    }

    @Override
    public void update(Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Roles> findAllTariffs() {
        return null;
    }

    @Override
    public Roles getRoleByName(String roleName) {
        return entityManager.createQuery("from Roles as role where role.name=:roleName", Roles.class).
                setParameter("roleName", roleName).getSingleResult();
    }
}
