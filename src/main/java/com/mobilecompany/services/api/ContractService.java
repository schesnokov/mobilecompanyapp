package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.entities.Contract;

import java.math.BigDecimal;
import java.util.List;

public interface ContractService {
    ContractDto getContract(Integer id);

    List<ContractDto> getAllContracts();

    void create(ContractDto contract);

    void update(ContractDto contract);

    void changeTariff(ContractChanges contractChanges, Integer contractId);

    void changeStatus(Integer contractId);

    void changeStatusByAdmin(Integer contractId);

    Contract getContractByPhone(String phone);

    BigDecimal getOrderResult(Integer tariffId, List<Integer> selectedOptionsIds);

    void delete(ContractDto contract);

}
