package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Contract;

import java.util.List;

public interface ContractDao {
    void create(Contract contract);

    Contract read(Integer id);

    void update(Contract contract);

    void delete(Contract contract);

    Contract findByPhoneNumber(String phone);

    List<Contract> findAllContracts();
}
