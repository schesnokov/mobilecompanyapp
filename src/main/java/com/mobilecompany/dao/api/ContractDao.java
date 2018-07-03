package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Contracts;

import java.util.List;

public interface ContractDao {
    void create(Contracts contract);
    Contracts read(Integer id);
    void update(Integer id);
    void delete(Integer id);
    List<Contracts> findAllContracts();
}
