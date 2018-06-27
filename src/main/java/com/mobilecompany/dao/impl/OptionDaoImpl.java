package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.entities.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
public class OptionDaoImpl implements OptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Option option) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Option option1 = new Option();
        entityManager.persist(option);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Option read(Integer a) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Option option = entityManager.find(Option.class, a);
        entityManager.close();
        return option;
    }

    @Override
    public void update(Option option) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Option option1 = entityManager.find(Option.class, 1);
        entityManager.detach(option);
        entityManager.getTransaction().begin();
        option.setName("NewName");
        option.setPrice(new BigDecimal(20.00));
        option.setConnectionCost(new BigDecimal(10.00));
        entityManager.close();
    }

    @Override
    public void delete(Integer id) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Option option = entityManager.find(Option.class, 1);
        entityManager.getTransaction().begin();
        entityManager.remove(option);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
