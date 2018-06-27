package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.entities.Option;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
public class OptionDaoImpl implements OptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void create(Option option) {
        Option option1 = new Option();
        entityManager.persist(option);
    }

    @Transactional
    @Override
    public Option read(Integer a) {
        Option option = entityManager.find(Option.class, a);
        return option;
    }

    @Transactional
    @Override
    public void update(Option option) {
        Option option1 = entityManager.find(Option.class, 1);
        entityManager.detach(option);
        option.setName("NewName");
        option.setPrice(new BigDecimal(20.00));
        option.setConnectionCost(new BigDecimal(10.00));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Option option = entityManager.find(Option.class, 1);
        entityManager.remove(option);
        }
}
