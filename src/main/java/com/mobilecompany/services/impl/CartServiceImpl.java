package com.mobilecompany.services.impl;

import com.mobilecompany.controllers.model.BucketChanges;
import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.services.api.CartService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The type Cart service.
 */
@Service
public class CartServiceImpl implements CartService {

    private static Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    private TariffService tariffService;
    private OptionService optionService;

    /**
     * Instantiates a new Cart service.
     *
     * @param tariffService the tariff service
     * @param optionService the option service
     */
    @Autowired
    public CartServiceImpl(TariffService tariffService, OptionService optionService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    /**
     * Add to cart.
     *
     * @param bucketChanges         the changes
     * @param contractId      the contract id
     * @param contractChanges the contract changes
     * @param ids             the ids
     */
    @Override
    public void addToCart(BucketChanges bucketChanges, Integer contractId, ContractChanges contractChanges, String ids) {
        contractChanges.setOptionsIds1(idsParse(ids));
        bucketChanges.setContractId(contractId);
        bucketChanges.setTariff(tariffService.getTariff(contractChanges.getTariffId()));
        Set<OptionDto> choosenOptions = new HashSet<>();
        for (Integer optionId : contractChanges.getOptionsIds1())  {
            choosenOptions.add(optionService.getOption(optionId));
        }
        bucketChanges.setOptions(choosenOptions);
        LOGGER.info("Adding new tariff and options to bucket for contract with id {}", contractId);
    }

    private List<Integer> idsParse(String ids) {
        Set<Integer> tmp = new HashSet<>();
        String[] stringIds = ids.split(",");
        for (String id : stringIds) {
            if (!(id.equals(""))) {
                tmp.add(Integer.valueOf(id));
            }
        }
        List<Integer> optionsIds = new ArrayList<>(tmp);
        return optionsIds;
    }
}
