package com.mobilecompany.controllers;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/contractPage/{id}", method = RequestMethod.GET)
    public String contract(@PathVariable Integer id, Model model) {
        ContractDto contractDto = contractService.getContract(id);
        model.addAttribute("contractDto", contractDto);
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("tariffDto", new TariffDto());
        return "/contractPage";
    }

    @RequestMapping(value = "/changeTariff/{id}", method = RequestMethod.GET)
    public String changeTariff(@ModelAttribute("tariffDto") TariffDto tariffDto, @PathVariable Integer id, Model model) {
        ContractDto contractDto = contractService.getContract(id);
        TariffDto newTariffDto = tariffService.getTariff(tariffDto.getId());
        contractDto.setTariff(newTariffDto);
        model.addAttribute("contractDto", contractDto);
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("tariffDto", new TariffDto());
        return "/contractPage";
    }
}
