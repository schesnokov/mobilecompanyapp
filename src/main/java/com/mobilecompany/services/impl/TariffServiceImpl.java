package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.entities.Tariff;
import com.mobilecompany.services.api.TariffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {

    private TariffDao tariffDao;
    private ModelMapper mapper;

    @Autowired
    public TariffServiceImpl(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public TariffDto getTariff(Integer id) {
        return mapper.map(tariffDao.read(id), TariffDto.class);
    }

    @Override
    @Transactional
    public List<TariffDto> getAllTariffs() {
        List<TariffDto> tariffDtoList = new ArrayList<>();
        for (Tariff tariff: tariffDao.findAllTariffs()) {
            tariffDtoList.add(mapper.map(tariff, TariffDto.class));
        }
        return tariffDtoList;
    }

    @Override
    @Transactional
    public void deleteTariff(Integer id) {
        tariffDao.delete(id);
    }

    @Override
    @Transactional
    public void addTariff(TariffDto tariff) {
        tariffDao.create(mapper.map(tariff, Tariff.class));
    }

}
