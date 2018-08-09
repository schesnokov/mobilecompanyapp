package com.mobilecompany.services.impl;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.services.api.CartService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public void addToCart(Map<Integer, ContractChanges> changes, Integer contractId, ContractChanges contractChanges) {
        changes.put(contractId, contractChanges);
    }
}
