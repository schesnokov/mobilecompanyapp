package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.ContractChanges;

import java.util.Map;

public interface CartService {
    void addToCart(Map<Integer, ContractChanges> changes, Integer contractId, ContractChanges contractChanges);
}
