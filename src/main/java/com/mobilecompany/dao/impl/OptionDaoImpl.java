package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.entities.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OptionDaoImpl implements OptionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Option option) {
        entityManager.persist(option);
    }

    @Override
    public Option read(Integer id) {
        return entityManager.find(Option.class, id);
    }

    @Override
    public void update(Integer id) {
        Option option = entityManager.find(Option.class, id);
        entityManager.merge(option);
    }

    @Override
    public void delete(Integer id) {
        Option option = entityManager.find(Option.class, id);
        entityManager.remove(option);
        }

    @Override
    public List<Option> findAllOptions() {
        return entityManager.createQuery("from Option c").getResultList();
    }
}
