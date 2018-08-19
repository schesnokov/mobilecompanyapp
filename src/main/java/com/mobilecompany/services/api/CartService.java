package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.BucketChanges;
import com.mobilecompany.controllers.model.ContractChanges;

public interface CartService {
    void addToCart(BucketChanges changes, Integer contractId, ContractChanges contractChanges, String ids);
}
