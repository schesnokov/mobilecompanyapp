package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.UserDao;
import com.mobilecompany.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
        LOGGER.info("{} have been created", user);
    }

    @Override
    public User read(Integer id) {
        LOGGER.info("Reading User by id {}", id);
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.merge(user);
        LOGGER.info("{} have been updated", user);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(User.class, id));
        LOGGER.info("User with id {} have been deleted", id);
    }

    public List<User> findAllUsers() {
        LOGGER.info("Finding all Users");
        return entityManager.createQuery("from User c").getResultList();
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.info("Find user by email: {}", email);
        Query query = entityManager.
                createQuery("from User as u1 where u1.email=:email",
                        User.class).setParameter("email", email);
        return (User) query.getSingleResult();
    }
}
