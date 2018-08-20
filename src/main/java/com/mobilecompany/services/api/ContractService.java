package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewContractHelper;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.entities.Contract;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Contract service.
 */
public interface ContractService {
    /**
     * Gets contract.
     *
     * @param id the id
     * @return the contract
     */
    ContractDto getContract(Integer id);

    /**
     * Gets all contracts.
     *
     * @return the all contracts
     */
    List<ContractDto> getAllContracts();

    /**
     * Create.
     *
     * @param newContract the new contract
     * @param customerId  the customer id
     */
    void create(NewContractHelper newContract, Integer customerId);

    /**
     * Update.
     *
     * @param contract the contract
     */
    void update(ContractDto contract);

    /**
     * Change tariff.
     *
     * @param contractChanges the contract changes
     * @param contractId      the contract id
     */
    void changeTariff(ContractChanges contractChanges, Integer contractId);

    /**
     * Change status.
     *
     * @param contractId the contract id
     */
    void changeStatus(Integer contractId);

    /**
     * Change status by admin.
     *
     * @param contractId the contract id
     */
    void changeStatusByAdmin(Integer contractId);

    /**
     * Gets contract by phone.
     *
     * @param phone the phone
     * @return the contract by phone
     */
    Contract getContractByPhone(String phone);

    /**
     * Gets order result.
     *
     * @param tariffId           the tariff id
     * @param selectedOptionsIds the selected options ids
     * @return the order result
     */
    BigDecimal getOrderResult(Integer tariffId, List<Integer> selectedOptionsIds);

    /**
     * Delete.
     *
     * @param contract the contract
     */
    void delete(ContractDto contract);

}
