package com.mobilecompany.services.api;

import com.mobilecompany.entities.Contract;

import java.util.List;

public interface ContractService {
    Contract getEntity(Integer id);
    List<Contract> getAllContracts();
    void create(Contract contract);
}
