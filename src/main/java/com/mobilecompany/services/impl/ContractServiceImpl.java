package com.mobilecompany.services.impl;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.entities.Contract;
import com.mobilecompany.entities.Option;
import com.mobilecompany.entities.Tariff;
import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.TariffService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    private static Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

    private ContractDao contractDao;
    private OptionDao optionDao;
    private TariffService tariffService;
    private ModelMapper mapper;

    @Autowired
    public ContractServiceImpl(ContractDao contractDao, OptionDao optionDao, TariffService tariffService) {
        this.contractDao = contractDao;
        this.optionDao = optionDao;
        this.tariffService = tariffService;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDto getContract(Integer id) {
        LOGGER.info("Getting contractDto of Entity with id {}", id);
        return mapper.map(contractDao.read(id), ContractDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractDto> getAllContracts() {
        LOGGER.info("Getting list with DTOs of contracts entities");
        List<ContractDto> contractDtoList = new ArrayList<>();
        for (Contract contract : contractDao.findAllContracts()) {
            contractDtoList.add(mapper.map(contract, ContractDto.class));
        }
        return contractDtoList;
    }

    @Override
    @Transactional
    public void create(ContractDto contract) {
        LOGGER.info("ContractDto mapped to Entity and passed to DAO");
        contractDao.create(mapper.map(contract, Contract.class));
    }

    @Override
    @Transactional
    public void update(ContractDto contract) {
        LOGGER.info("Sending {} to DAO for update", contract);
        Contract updatedContract = mapper.map(contract, Contract.class);
        contractDao.update(updatedContract);
    }

    @Override
    @Transactional
    public void changeTariff(ContractChanges contractChanges, Integer contractId) {
        LOGGER.info("Changing tariff of contract with id {}", contractId);
        Contract contract = contractDao.read(contractId);
        Set<Option> selectedOptions = new HashSet<>();
        for (Integer optionId : contractChanges.getOptionsIds()) {
            selectedOptions.add(optionDao.read(optionId));
        }
        contract.setSelectedOptions(selectedOptions);
        Integer tariffId = contractChanges.getTariffId();
        Tariff tariff = mapper.map(tariffService.getTariff(tariffId), Tariff.class);
        contract.setTariff(tariff);
        contract.setBalance(contract.getBalance().subtract(getOrderResult(tariffId, contractChanges.getOptionsIds())));
        contractDao.update(contract);
    }

    @Override
    public BigDecimal getOrderResult(Integer tariffId, List<Integer> selectedOptionsIds) {
        LOGGER.info("Calculation of price for tariff changes");
        Tariff tariff = mapper.map(tariffService.getTariff(tariffId), Tariff.class);
        BigDecimal tariffPrice = tariff.getTariffPrice();
        BigDecimal changeCost = new BigDecimal(0.0);
        changeCost = changeCost.add(tariffPrice);
        Set<Option> selectedOptions = new HashSet<>();
        for (Integer optionId : selectedOptionsIds) {
            selectedOptions.add(optionDao.read(optionId));
        }
        for (Option option : selectedOptions) {
            changeCost = changeCost.add(option.getPrice()).add(option.getConnectionCost());
        }
        return changeCost;
    }

    @Override
    @Transactional
    public void changeStatus(Integer contractId) {
        LOGGER.info("Changing tariff status with id {} by user", contractId);
        Contract contract = contractDao.read(contractId);
        if (contract.getIsBlocked() == 0) {
            contract.setIsBlocked(1);
        } else {
            contract.setIsBlocked(0);
        }
        contractDao.update(contract);
    }

    @Override
    @Transactional
    public void changeStatusByAdmin(Integer contractId) {
        LOGGER.info("Changing tariff status with id {} by admin", contractId);
        Contract contract = contractDao.read(contractId);
        if (contract.getIsBlocked() == 0) {
            contract.setIsBlocked(2);
        } else {
            contract.setIsBlocked(0);
        }
    }

    @Override
    @Transactional
    public Contract getContractByPhone(String phone) {
        LOGGER.info("Passing phone number {} to DAO for finding contract", phone);
        return contractDao.findByPhoneNumber(phone);
    }

    @Override
    @Transactional
    public void delete(ContractDto contractDto) {
        LOGGER.info("Passing contractDto with id {} to DAO for delete", contractDto.getId());
        contractDao.delete(mapper.map(contractDto, Contract.class));
    }
}
