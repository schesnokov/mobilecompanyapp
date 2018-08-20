package com.mobilecompany.services.impl;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewContractHelper;
import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.dao.api.OptionDao;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.Contract;
import com.mobilecompany.entities.Option;
import com.mobilecompany.entities.Tariff;
import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
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

/**
 * The Contract service.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private static Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

    private ContractDao contractDao;
    private OptionDao optionDao;
    private TariffService tariffService;
    private UserService userService;
    private OptionService optionService;
    private ModelMapper mapper;

    /**
     * Instantiates a new Contract service.
     *
     * @param contractDao   the contract dao
     * @param optionDao     the option dao
     * @param optionService the option service
     * @param tariffService the tariff service
     * @param userService   the user service
     */
    @Autowired
    public ContractServiceImpl(ContractDao contractDao, OptionDao optionDao, OptionService optionService,
                               TariffService tariffService, UserService userService) {
        this.contractDao = contractDao;
        this.optionDao = optionDao;
        this.tariffService = tariffService;
        this.userService = userService;
        this.optionService = optionService;
        mapper = new ModelMapper();
    }

    /**
     * Gets contract.
     *
     * @param id the id
     * @return the contract
     */
    @Override
    @Transactional(readOnly = true)
    public ContractDto getContract(Integer id) {
        LOGGER.info("Getting contractDto of Entity with id {}", id);
        return mapper.map(contractDao.read(id), ContractDto.class);
    }

    /**
     * Gets all contracts.
     *
     * @return the all contracts
     */
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

    /**
     * Create.
     *
     * @param newContract the new contract
     * @param customerId  the customer id
     */
    @Override
    @Transactional
    public void create(NewContractHelper newContract, Integer customerId) {
        LOGGER.info("ContractDto mapped to Entity and passed to DAO");
        UserDto customer = userService.getUser(customerId);
        ContractDto contract = new ContractDto();
        Set<OptionDto> optionDtos = new HashSet<>();
        contract.setNumber(newContract.getNumber());
        contract.setBalance(newContract.getBalance());
        contract.setTariffDto(tariffService.getTariff(newContract.getTariffId()));
        for (Integer optionId : newContract.getOptionsIds()) {
            optionDtos.add(optionService.getOption(optionId));
        }
        contract.setSelectedOptions(optionDtos);
        contract.setUserDto(customer);
        customer.getContracts().add(contract);
        userService.update(customer);
        contractDao.create(mapper.map(contract, Contract.class));
    }

    /**
     * Update.
     *
     * @param contract the contract
     */
    @Override
    @Transactional
    public void update(ContractDto contract) {
        LOGGER.info("Sending {} to DAO for update", contract);
        Contract updatedContract = mapper.map(contract, Contract.class);
        contractDao.update(updatedContract);
    }

    /**
     * Change tariff.
     *
     * @param contractChanges the contract changes
     * @param contractId      the contract id
     */
    @Override
    @Transactional
    public void changeTariff(ContractChanges contractChanges, Integer contractId) {
        LOGGER.info("Changing tariff of contract with id {}", contractId);
        Contract contract = contractDao.read(contractId);
        Set<Option> selectedOptions = new HashSet<>();
        for (Integer optionId : contractChanges.getOptionsIds1()) {
            selectedOptions.add(optionDao.read(optionId));
        }
        contract.setSelectedOptions(selectedOptions);
        Integer tariffId = contractChanges.getTariffId();
        Tariff tariff = mapper.map(tariffService.getTariff(tariffId), Tariff.class);
        contract.setTariff(tariff);
        contract.setBalance(contract.getBalance().subtract(getOrderResult(tariffId, contractChanges.getOptionsIds1())));
        contractDao.update(contract);
    }

    /**
     * Calculates the price of changes for contract
     *
     * @param tariffId           the tariff id
     * @param selectedOptionsIds the selected options ids
     * @return the order result
     */
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

    /**
     * Change status of contract by User.
     *
     * @param contractId the contract id
     */
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

    /**
     * Change status by admin.
     *
     * @param contractId the contract id
     */
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

    /**
     * Gets contract by phone.
     *
     * @param phone the phone
     * @return the contract by phone
     */
    @Override
    @Transactional
    public Contract getContractByPhone(String phone) {
        LOGGER.info("Passing phone number {} to DAO for finding contract", phone);
        return contractDao.findByPhoneNumber(phone);
    }

    /**
     * Delete.
     *
     * @param contractDto the contract
     */
    @Override
    @Transactional
    public void delete(ContractDto contractDto) {
        LOGGER.info("Passing contractDto with id {} to DAO for delete", contractDto.getId());
        contractDao.delete(mapper.map(contractDto, Contract.class));
    }
}
