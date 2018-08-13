package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.entities.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TariffDaoImpl implements TariffDao {

    private static Logger LOGGER = LoggerFactory.getLogger(TariffDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Tariff tariff) {
        entityManager.persist(tariff);
        LOGGER.info("{} have been created", tariff);
    }

    @Override
    public Tariff read(Integer id) {
        LOGGER.info("Reading tariff by id {}", id);
        return entityManager.find(Tariff.class, id);
    }

    @Override
    public void update(Tariff tariff) {
        entityManager.merge(tariff);
        LOGGER.info("{} have been updated", tariff);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(Tariff.class, id));
        LOGGER.info("Tariff with id {} have been deleted", id);
    }

    public List<Tariff> findAllTariffs() {
        LOGGER.info("Reading all tariff");
        return entityManager.createQuery("from Tariff c").getResultList();
    }

}


