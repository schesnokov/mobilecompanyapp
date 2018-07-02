package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.TariffDao;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.entities.Tariffs;
import com.mobilecompany.services.api.TariffsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TariffDto getEntity(Integer id) {
        Tariffs tariffs = tariffDao.read(id);
        return mapper.map(tariffs, TariffDto.class);
    }

    @Override
    public String getAllTariffs() {
        List<Tariffs> tariffsList = tariffDao.findAllTariffs();
        String result = "";
        for (Tariffs tariff : tariffsList) {
            if (tariff.getOptions() != null) {
                result = result + " " + tariff + "\n";
            } else {
                result = result + " " + tariff.getName() +  " tariff have no options" + "\n";
            }
        }
        return result;
    }
}
