package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.entities.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The Contract dao.
 */
@Repository
public class ContractDaoImpl implements ContractDao {

    private static Logger LOGGER = LoggerFactory.getLogger(ContractDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create.
     *
     * @param contract the contract
     */
    @Override
    public void create(Contract contract) {
        entityManager.persist(contract);
        LOGGER.info("Contract {} have been created.", contract);
    }

    /**
     * Read contract.
     *
     * @param id the id
     * @return the contract
     */
    @Override
    public Contract read(Integer id) {
        LOGGER.info("Reading contract by id {}", id);
        return entityManager.find(Contract.class, id);
    }

    /**
     * Update.
     *
     * @param contract the contract
     */
    @Override
    public void update(Contract contract) {
        entityManager.merge(contract);
        LOGGER.info("{} have been updated", contract);
    }

    /**
     * Delete.
     *
     * @param contract the contract
     */
    @Override
    public void delete(Contract contract) {
        entityManager.remove(contract);
        LOGGER.info("{} have been removed", contract);
    }

    /**
     * Find by phone number contract.
     *
     * @param phone the phone
     * @return the contract
     */
    @Override
    public Contract findByPhoneNumber(String phone) {
        LOGGER.info("Finding contract by phone {}", phone);
        return entityManager.createQuery("from Contract as contract where contract.number = :phone", Contract.class).
                setParameter("phone", phone).getSingleResult();
    }

    /**
     * Find all contracts list.
     *
     * @return the list of contracts
     */
    @Override
    public List<Contract> findAllContracts() {
        LOGGER.info("Finding all contracts");
        return entityManager.createQuery("from Contract c").getResultList();
    }

}
