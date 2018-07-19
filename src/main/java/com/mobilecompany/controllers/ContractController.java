package com.mobilecompany.controllers;

import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContractController {

    private ContractService contractService;
    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;

    @Autowired
    public ContractController(ContractService contractService, UserService userService, TariffService tariffService, OptionService optionService) {
        this.contractService = contractService;
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

}
