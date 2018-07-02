package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.UsersDao;
import com.mobilecompany.entities.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Users user) {
        entityManager.persist(user);
    }

    @Override
    public Users read(Integer id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public void update(Integer id) {
        Users user = entityManager.find(Users.class, id);
        entityManager.detach(user);
        user.setFirstName("New name");
        entityManager.merge(user);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(Users.class, id));
    }

    public List<Users> findAllUsers() {
        return entityManager.createQuery("from Users c").getResultList();
    }
}
