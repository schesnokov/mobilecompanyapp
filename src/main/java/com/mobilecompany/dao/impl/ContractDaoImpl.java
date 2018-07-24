package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.entities.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Contract contract) {
        entityManager.persist(contract);
    }

    @Override
    public Contract read(Integer id) {
        return entityManager.find(Contract.class, id);
    }

    @Override
    public void update(Contract contract) {
        Contract newContract = entityManager.find(Contract.class, contract.getId());
        entityManager.merge(newContract);
    }

    @Override
    public void delete(Integer id) {
        Contract contract = entityManager.find(Contract.class, id);
        entityManager.remove(contract);
    }

    @Override
    public List<Contract> findAllContracts() {
        return entityManager.createQuery("from Contract c").getResultList();
    }
}
