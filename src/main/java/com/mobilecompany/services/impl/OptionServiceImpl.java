package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Option;
import com.mobilecompany.services.api.OptionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private static Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);

    private OptionDao optionDao;
    private ModelMapper mapper;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao) {
        this.optionDao = optionDao;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDto getOption(Integer id) {
        LOGGER.info("Getting optionDto of Entity with id {}", id);
        return mapper.map(optionDao.read(id), OptionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getAllOptions() {
        LOGGER.info("Getting list of optionDto with all options");
        List<OptionDto> optionDtoList = new ArrayList<>();
        for (Option option : optionDao.findAllOptions()) {
            optionDtoList.add(mapper.map(option, OptionDto.class));
        }
        return optionDtoList;
    }

    @Override
    @Transactional
    public void addOption(OptionDto option) {
        LOGGER.info("Passing new Option Entity to DAO from DTO");
        optionDao.create(mapper.map(option, Option.class));
    }
}
