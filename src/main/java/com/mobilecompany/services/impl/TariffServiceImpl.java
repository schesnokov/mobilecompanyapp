package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.entities.Tariff;
import com.mobilecompany.services.api.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {

    private TariffDao tariffDao;

    @Autowired
    public TariffServiceImpl(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    @Override
    @Transactional
    public Tariff getTariff(Integer id) {
        return tariffDao.read(id);
    }

    @Override
    @Transactional
    public List<Tariff> getAllTariffs() {
        return tariffDao.findAllTariffs();
    }

    @Override
    @Transactional
    public void deleteTariff(Integer id) {
        tariffDao.delete(id);
    }

    @Override
    @Transactional
    public void addTariff(Tariff tariff) {
        tariffDao.create(tariff);
    }
}
