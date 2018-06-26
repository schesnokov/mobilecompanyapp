package com.mobilecompany.dao;

import com.mobilecompany.entities.Option;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
public class OptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create() {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Option option = new Option();
        entityManager.persist(option);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Option read(Integer a) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Option option = entityManager.find(Option.class, a);
        entityManager.close();
        return option;
    }

    public void update() {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Option option = entityManager.find(Option.class, 1);
        entityManager.detach(option);
        entityManager.getTransaction().begin();
        option.setName("NewName");
        option.setPrice(new BigDecimal(20.00));
        option.setConnectionCost(new BigDecimal(10.00));
        entityManager.close();
    }

    public void delete() {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        Option option = entityManager.find(Option.class, 1);
        entityManager.getTransaction().begin();
        entityManager.remove(option);
        entityManager.getTransaction().commit();
        entityManager.close();
        }
}
