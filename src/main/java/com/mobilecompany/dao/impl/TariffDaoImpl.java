package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.entities.Tariff;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TariffDaoImpl implements TariffDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Tariff tariff) {
        entityManager.persist(tariff);
    }

    @Override
    public Tariff read(Integer id) {
        return entityManager.find(Tariff.class, id);
    }

    @Override
    public void update(Integer id) {
        Tariff tariff = entityManager.find(Tariff.class, id);
        entityManager.detach(tariff);
        tariff.setTariffName("New name");
        entityManager.merge(tariff);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(Tariff.class, id));
    }

    public List<Tariff> findAllTariffs() {
        return entityManager.createQuery("from Tariff c").getResultList();
    }

}


