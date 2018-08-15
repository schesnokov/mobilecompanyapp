package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.entities.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {

    private static Logger LOGGER = LoggerFactory.getLogger(ContractDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Contract contract) {
        entityManager.persist(contract);
        LOGGER.info("Contract {} have been created.", contract);
    }

    @Override
    public Contract read(Integer id) {
        LOGGER.info("Reading contract by id {}", id);
        return entityManager.find(Contract.class, id);
    }

    @Override
    public void update(Contract contract) {
        entityManager.merge(contract);
        LOGGER.info("{} have been updated", contract);
    }

    @Override
    public void delete(Contract contract) {
        entityManager.remove(contract);
        LOGGER.info("{} have been removed", contract);
    }

    @Override
    public Contract findByPhoneNumber(String phone) {
        LOGGER.info("Finding contract by phone {}", phone);
        return entityManager.createQuery("from Contract as contract where contract.number = :phone", Contract.class).
                setParameter("phone", phone).getSingleResult();
    }

    @Override
    public List<Contract> findAllContracts() {
        LOGGER.info("Finding all contracts");
        return entityManager.createQuery("from Contract c").getResultList();
    }

}
