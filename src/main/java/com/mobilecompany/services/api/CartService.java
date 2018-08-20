package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.BucketChanges;
import com.mobilecompany.controllers.model.ContractChanges;

/**
 * The interface Cart service.
 */
public interface CartService {
    /**
     * Add to cart.
     *
     * @param changes         the changes
     * @param contractId      the contract id
     * @param contractChanges the contract changes
     * @param ids             the ids
     */
    void addToCart(BucketChanges changes, Integer contractId, ContractChanges contractChanges, String ids);
}
