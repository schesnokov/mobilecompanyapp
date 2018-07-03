package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.entities.Contracts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Contracts contract) {
        entityManager.persist(contract);
    }

    @Override
    public Contracts read(Integer id) {
        return entityManager.find(Contracts.class, id);
    }

    @Override
    public void update(Integer id) {
        Contracts contract = entityManager.find(Contracts.class, id);
        entityManager.detach(contract);
        contract.setNumber("NewNumber");
        entityManager.merge(contract);
    }

    @Override
    public void delete(Integer id) {
        Contracts contract = entityManager.find(Contracts.class, id);
        entityManager.remove(contract);
    }

    @Override
    public List<Contracts> findAllContracts() {
        return entityManager.createQuery("from Contracts c").getResultList();
    }
}
