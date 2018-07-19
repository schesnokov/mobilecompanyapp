package com.mobilecompany.services.api;

import com.mobilecompany.dto.ContractDto;

import java.util.List;

public interface ContractService {
    ContractDto getContract(Integer id);
    List<ContractDto> getAllContracts();
    void create(ContractDto contract);
}
