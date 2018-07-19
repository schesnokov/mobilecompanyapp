package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.entities.Contract;
import com.mobilecompany.services.api.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractDao contractDao;
    private ModelMapper mapper;

    @Autowired
    public ContractServiceImpl(ContractDao contractDao) {
        this.contractDao = contractDao;
        mapper = new ModelMapper();
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDto getContract(Integer id) {
        return mapper.map(contractDao.read(id), ContractDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractDto> getAllContracts() {
        List<ContractDto> contractDtoList = new ArrayList<>();
        for(Contract contract: contractDao.findAllContracts()) {
            contractDtoList.add(mapper.map(contract, ContractDto.class));
        }
        return contractDtoList;
    }

    @Override
    @Transactional
    public void create(ContractDto contract) {
        contractDao.create(mapper.map(contract, Contract.class));
    }
}
