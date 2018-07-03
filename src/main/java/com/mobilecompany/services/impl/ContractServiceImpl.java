package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.entities.Contracts;
import com.mobilecompany.services.api.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ContractDto getEntity(Integer id) {
        Contracts contract = contractDao.read(id);
        return mapper.map(contract, ContractDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public String getAllContracts() {
        List<Contracts> contractsList = contractDao.findAllContracts();
        String result = "";
        for (Contracts contract : contractsList) {
            result = result + " " + contract;
        }
        return result;
    }
}
