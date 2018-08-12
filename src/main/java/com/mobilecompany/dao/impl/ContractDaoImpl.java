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
        entityManager.merge(contract);
    }

    @Override
    public void delete(Contract contract) {
        entityManager.remove(contract);
    }

    @Override
    public Contract findByPhoneNumber(String phone) {
        return entityManager.createQuery("from Contract as contract where contract.number = :phone", Contract.class).
                setParameter("phone", phone).getSingleResult();
    }

    @Override
    public List<Contract> findAllContracts() {
        return entityManager.createQuery("from Contract c").getResultList();
    }
}
