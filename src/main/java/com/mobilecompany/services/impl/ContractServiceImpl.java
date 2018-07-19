package com.mobilecompany.services.impl;

import com.mobilecompany.dao.api.ContractDao;
import com.mobilecompany.entities.Contracts;
import com.mobilecompany.services.api.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractDao contractDao;

    @Autowired
    public ContractServiceImpl(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Contracts getEntity(Integer id) {
        return contractDao.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contracts> getAllContracts() {
        return contractDao.findAllContracts();
    }

    @Override
    @Transactional
    public void create(Contracts contract) {
        contractDao.create(contract);
    }
}
