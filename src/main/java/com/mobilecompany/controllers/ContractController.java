package com.mobilecompany.controllers;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContractController {

    private ContractService contractService;
    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;
    private SecurityService securityService;

    @Autowired
    public ContractController(SecurityService securityService, ContractService contractService, UserService userService, TariffService tariffService, OptionService optionService) {
        this.contractService = contractService;
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/contractPage/{id}", method = RequestMethod.GET)
    public String contract(@PathVariable Integer id, Model model) {
        String userEmail = securityService.findLoggedInEmail();
        ContractDto contractDto = contractService.getContract(id);
        model.addAttribute("contractDto", contractDto);
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("customer", userService.findByEmail(userEmail));
        return "/contractPage";
    }

    @RequestMapping(value = "/changeTariff/{contractId}", method = RequestMethod.GET)
    public String changeTariff(@ModelAttribute("tariffDto") TariffDto tariffDto,
                               @PathVariable Integer contractId, Model model) {
        String userEmail = securityService.findLoggedInEmail();
        contractService.changeTariff(tariffDto.getId(), contractId);
        model.addAttribute("contractDto", contractService.getContract(contractId));
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("customer", userService.findByEmail(userEmail));
        return "/contractPage";
    }
}
