package com.mobilecompany.dao.api;

import com.mobilecompany.entities.Contract;

import java.util.List;

/**
 * The interface Contract dao.
 */
public interface ContractDao {
    /**
     * Create.
     *
     * @param contract the contract
     */
    void create(Contract contract);

    /**
     * Read contract.
     *
     * @param id the id
     * @return the contract
     */
    Contract read(Integer id);

    /**
     * Update.
     *
     * @param contract the contract
     */
    void update(Contract contract);

    /**
     * Delete.
     *
     * @param contract the contract
     */
    void delete(Contract contract);

    /**
     * Find by phone number contract.
     *
     * @param phone the phone
     * @return the contract
     */
    Contract findByPhoneNumber(String phone);

    /**
     * Find all contracts list.
     *
     * @return the list of contracts
     */
    List<Contract> findAllContracts();
}
