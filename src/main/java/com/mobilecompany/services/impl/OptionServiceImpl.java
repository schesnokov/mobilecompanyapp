package com.mobilecompany.services.impl;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.entities.Option;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OptionServiceImpl implements OptionService {

    private static Logger LOGGER = LoggerFactory.getLogger(OptionServiceImpl.class);

    private OptionDao optionDao;
    private TariffService tariffService;
    private ModelMapper mapper;

    @Autowired
    public OptionServiceImpl(OptionDao optionDao, TariffService tariffService) {
        this.optionDao = optionDao;
        this.tariffService = tariffService;
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
    public void addOption(NewOptionHelper newOption) {
        LOGGER.info("Passing new Option Entity to DAO from DTO");
        OptionDto optionDto = new OptionDto();
        optionDto.setName(newOption.getName());
        optionDto.setDescription(newOption.getDescription());
        optionDto.setPrice(newOption.getPrice());
        optionDto.setConnectionCost(newOption.getConnectionCost());
        Set<OptionDto> dependentOptions = new HashSet<>();
        for (Integer dependentOptionId : newOption.getDependentIds()) {
            dependentOptions.add(mapper.map(optionDao.read(dependentOptionId), OptionDto.class));
        }
        Set<OptionDto> conflictedOptions = new HashSet<>();
        for (Integer conflictedOptionId : newOption.getConflictedIds()) {
            conflictedOptions.add(mapper.map(optionDao.read(conflictedOptionId), OptionDto.class));
        }
        optionDto.setDependentFirst(dependentOptions);
        optionDto.setConflictedFirst(conflictedOptions);
        optionDao.create(mapper.map(optionDto, Option.class));
    }

    @Override
    @Transactional
    public void changeOptionStatus(Integer optionId) {
        LOGGER.info("Blocking option with id {}", optionId);
        Option option = optionDao.read(optionId);
        option.setOptionIsBlocked(1);
        optionDao.update(option);
    }

    @Override
    @Transactional
    public TariffDto addAvailableOptions(Integer tariffId, ContractChanges contractChanges) {
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        Set<OptionDto> availableOptions = new HashSet<>();
        availableOptions.addAll(tariffDto.getAvailableOptions());
        for (Integer optionId : contractChanges.getOptionsIds2()) {
            OptionDto optionDto = mapper.map(optionDao.read(optionId), OptionDto.class);
            availableOptions.add(optionDto);
        }
        tariffDto.setAvailableOptions(availableOptions);
        return tariffDto;
    }

    @Override
    @Transactional
    public TariffDto deleteAvailableOptions(Integer tariffId, ContractChanges contractChanges) {
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        Set<OptionDto> availableOptions = tariffDto.getAvailableOptions();
        Set<OptionDto> optionToRemove = new HashSet<>();
        for (Integer optionId : contractChanges.getOptionsIds1()) {
            OptionDto optionDto = mapper.map(optionDao.read(optionId), OptionDto.class);
            optionToRemove.add(optionDto);
        }
        availableOptions.removeAll(optionToRemove);
        tariffDto.setAvailableOptions(availableOptions);
        return tariffDto;
    }

    @Override
    @Transactional
    public List<Integer> getConflictedOptions(Integer optionId) {
        List<OptionDto> conflictedOptions = new ArrayList<>();
        OptionDto optionDto = mapper.map(optionDao.read(optionId), OptionDto.class);
        conflictedOptions.addAll(optionDto.getConflictedFirst());
        List<Integer> conflictedOptionsIds = new ArrayList<>();
        for (OptionDto option : conflictedOptions) {
            conflictedOptionsIds.add(option.getId());
        }
        return conflictedOptionsIds;
    }


    @Override
    @Transactional
    public List<Integer> getDependentOptions(Integer optionId) {
        List<OptionDto> dependentOptions = new ArrayList<>();
        OptionDto optionDto = mapper.map(optionDao.read(optionId), OptionDto.class);
        dependentOptions.addAll(optionDto.getDependentFirst());
        List<Integer> dependentOptionsIds = new ArrayList<>();
        for (OptionDto option : dependentOptions) {
            dependentOptionsIds.add(option.getId());
        }
        return dependentOptionsIds;
    }
}
