package com.mobilecompany.dao.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.entities.Tariffs;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TariffDaoImpl implements TariffDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Tariffs tariff) {
        entityManager.persist(tariff);
    }

    @Override
    public Tariffs read(Integer id) {
        return entityManager.find(Tariffs.class, id);
    }

    @Override
    public void update(Integer id) {
        Tariffs tariff = entityManager.find(Tariffs.class, id);
        entityManager.detach(tariff);
        tariff.setName("New name");
        entityManager.merge(tariff);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(Tariffs.class, id));
    }

    public List<Tariffs> findAllTariffs() {
        return entityManager.createQuery("from Tariffs c").getResultList();
    }
}

