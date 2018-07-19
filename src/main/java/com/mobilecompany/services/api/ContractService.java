package com.mobilecompany.services.api;

import com.mobilecompany.entities.Contracts;

import java.util.List;

public interface ContractService {
    Contracts getEntity(Integer id);
    List<Contracts> getAllContracts();
    void create(Contracts contract);
}
