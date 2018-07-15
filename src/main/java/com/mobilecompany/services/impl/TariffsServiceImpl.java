package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.entities.Tariffs;
import com.mobilecompany.services.api.TariffsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffsServiceImpl implements TariffsService {

    private TariffDao tariffDao;
    private ModelMapper mapper;

    @Autowired
    public TariffsServiceImpl(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public Tariffs getEntity(Integer id) {
        Tariffs tariffs = tariffDao.read(id);
        return tariffs;
    }

    @Override
    @Transactional
    public List<Tariffs> getAllTariffs() {
        List<Tariffs> tariffsList = tariffDao.findAllTariffs();
        return tariffsList;
    }

    @Override
    @Transactional
    public void deleteTariff(Integer id) {
        tariffDao.delete(id);
    }

    @Override
    @Transactional
    public void addTariff(Tariffs tariffs) {
        tariffDao.create(tariffs);
    }
}
