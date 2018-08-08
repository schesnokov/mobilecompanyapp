package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.entities.Option;
import com.mobilecompany.services.api.OptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public OptionDto getOption(Integer id) {
        return mapper.map(optionDao.read(id), OptionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionDto> getAllOptions() {
        List<OptionDto> optionDtoList = new ArrayList<>();
        for (Option option : optionDao.findAllOptions()) {
            optionDtoList.add(mapper.map(option, OptionDto.class));
        }
        return optionDtoList;
    }

    @Override
    @Transactional
    public void addOption(OptionDto option) {
        optionDao.create(mapper.map(option, Option.class));
    }
}
