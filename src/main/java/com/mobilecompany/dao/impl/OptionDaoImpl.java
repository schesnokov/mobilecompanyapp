package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.entities.Options;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class OptionDaoImpl implements OptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Options option) {
        entityManager.persist(option);
    }

    @Override
    public Options read(Integer id) {
        return entityManager.find(Options.class, id);
    }

    @Override
    public void update(Integer id) {
        Options option = entityManager.find(Options.class, id);
        entityManager.detach(option);
        option.setName("NewName");
        option.setPrice(new BigDecimal(20.00));
        option.setConnectionCost(new BigDecimal(10.00));
        entityManager.merge(option);
    }

    @Override
    public void delete(Integer id) {
        Options option = entityManager.find(Options.class, id);
        entityManager.remove(option);
        }

    @Override
    public List<Options> findAllOptions() {
        return entityManager.createQuery("from Options c").getResultList();
    }
}
