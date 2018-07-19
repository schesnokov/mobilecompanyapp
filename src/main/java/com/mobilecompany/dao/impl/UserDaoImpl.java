package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.UserDao;
import com.mobilecompany.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public User read(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        entityManager.merge(user);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery("from User c").getResultList();
    }

    @Override
    public User getByEmail(String email) {
        Query query = entityManager.
                createQuery("from User as u1 where u1.email=:email",
                        User.class).setParameter("email", email);
        return (User) query.getSingleResult();
    }
}
