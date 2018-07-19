package com.mobilecompany.controllers;

import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private OptionService optionService;
    private UserService userService;
    private TariffService tariffService;
    private ContractService contractService;

    @Autowired
    public IndexController(ContractService contractService, OptionService optionService, UserService userService, TariffService tariffService) {
        this.optionService = optionService;
        this.userService = userService;
        this.tariffService = tariffService;
        this.contractService = contractService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }
}
