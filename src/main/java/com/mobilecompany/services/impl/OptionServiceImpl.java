package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Options;
import com.mobilecompany.services.api.OptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private OptionDao optionDao;
    private ModelMapper mapper;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao) {
        this.optionDao = optionDao;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDto getEntity(Integer id) {
        Options optionEntity = optionDao.read(id);
        return mapper.map(optionEntity, OptionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllOptions() {
        List<Options> optionsList = optionDao.findAllOptions();
        String result = "";
        for (Options option : optionsList) {
            result = result + " " + option;
        }
        return result;
    }

    @Override
    @Transactional
    public void addOption(Options option) {
        optionDao.create(option);
    }
}
