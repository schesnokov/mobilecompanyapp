package com.mobilecompany.controllers;

import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffsService;
import com.mobilecompany.services.api.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private OptionService optionService;
    private UsersService usersService;
    private TariffsService tariffsService;
    private ContractService contractService;

    @Autowired
    public IndexController(ContractService contractService, OptionService optionService, UsersService usersService, TariffsService tariffsService) {
        this.optionService = optionService;
        this.usersService = usersService;
        this.tariffsService = tariffsService;
        this.contractService = contractService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }
}
