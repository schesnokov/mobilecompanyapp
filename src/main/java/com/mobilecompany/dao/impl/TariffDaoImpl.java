package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.entities.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The Tariff dao.
 */
@Repository
public class TariffDaoImpl implements TariffDao {

    private static Logger LOGGER = LoggerFactory.getLogger(TariffDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create.
     *
     * @param tariff the tariff
     */
    @Override
    public void create(Tariff tariff) {
        entityManager.persist(tariff);
        LOGGER.info("{} have been created", tariff);
    }

    /**
     * Read tariff.
     *
     * @param id the id
     * @return the tariff
     */
    @Override
    public Tariff read(Integer id) {
        LOGGER.info("Reading tariff by id {}", id);
        return entityManager.find(Tariff.class, id);
    }

    /**
     * Update.
     *
     * @param tariff the tariff
     */
    @Override
    public void update(Tariff tariff) {
        entityManager.merge(tariff);
        LOGGER.info("{} have been updated", tariff);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(Tariff.class, id));
        LOGGER.info("Tariff with id {} have been deleted", id);
    }

    /**
     * Find all tariffs list.
     *
     * @return the list of tariffs
     */
    public List<Tariff> findAllTariffs() {
        LOGGER.info("Reading all tariff");
        return entityManager.createQuery("from Tariff as tariff where tariff.tariffIsBlocked = 0").getResultList();
    }

}


