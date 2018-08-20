package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.entities.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The Option dao.
 */
@Repository
public class OptionDaoImpl implements OptionDao {

    private static Logger LOGGER = LoggerFactory.getLogger(OptionDaoImpl.class);


    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create.
     *
     * @param option the option
     */
    @Override
    public void create(Option option) {
        entityManager.persist(option);
        LOGGER.info("{} have been created");
    }

    /**
     * Read option.
     *
     * @param id the id
     * @return the option
     */
    @Override
    public Option read(Integer id) {
        LOGGER.info("Reading option by id {}", id);
        return entityManager.find(Option.class, id);
    }

    /**
     * Update.
     *
     * @param option the option
     */
    @Override
    public void update(Option option) {
        entityManager.merge(option);
        LOGGER.info("{} have been updated", option);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    public void delete(Integer id) {
        Option option = entityManager.find(Option.class, id);
        entityManager.remove(option);
        LOGGER.info("{} have been deleted", option);
    }

    /**
     * Find all options list.
     *
     * @return the list of options
     */
    @Override
    public List<Option> findAllOptions() {
        LOGGER.info("Reading all options");
        return entityManager.createQuery("from Option as option where option.optionIsBlocked = 0").getResultList();
    }
}
