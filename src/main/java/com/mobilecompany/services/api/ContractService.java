package com.mobilecompany.services.api;

import com.mobilecompany.dto.ContractDto;

public interface ContractService {
    ContractDto getEntity(Integer id);
    String getAllContracts();
}
