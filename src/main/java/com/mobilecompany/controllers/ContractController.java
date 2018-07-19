package com.mobilecompany.controllers;

import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffsService;
import com.mobilecompany.services.api.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ContractController {

    private ContractService contractService;
    private UsersService usersService;
    private TariffsService tariffsService;
    private OptionService optionService;

    @Autowired
    public ContractController(ContractService contractService,UsersService usersService,TariffsService tariffsService, OptionService optionService) {
        this.contractService = contractService;
        this.usersService = usersService;
        this.tariffsService = tariffsService;
        this.optionService = optionService;
    }

}
