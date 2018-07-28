package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionAttributes(value = {"contractDto", "tariffList"})
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
        model.addAttribute("contractChanges", new ContractChanges());
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("availableOptions", contractDto.getTariff().getAvailableOptions());
        return "/contractPage";
    }

    @RequestMapping(value = "/changeTariff/{contractId}", method = RequestMethod.POST)
    public String changeTariff(@ModelAttribute("contractChanges") ContractChanges contractChanges,
                               @PathVariable(name = "contractId") Integer contractId, Model model) {
        contractService.changeTariff(contractChanges.getTariffId(), contractId);
        ContractDto contract = contractService.getContract(contractId);
        model.addAttribute("contractDto", contract);
        model.addAttribute("availableOptions", contract.getTariff().getAvailableOptions());
        return "/contractPage";
    }

    @RequestMapping(value = "/options/{tariffId}", method = RequestMethod.GET)
    @ResponseBody
    public Set<OptionDto> getOptionsByTariff(@PathVariable(name = "tariffId") Integer tariffId) {
        Set<OptionDto> availableOptions = new HashSet<>();
        availableOptions = tariffService.getTariff(tariffId).getAvailableOptions();
        return availableOptions;
    }

    @RequestMapping(value = "/options/conflict/{optionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getConflictedOptions(@PathVariable(name = "optionId") Integer optionId) {
        List<OptionDto> conflictedOptions = new ArrayList<>(optionService.getOption(optionId).getConflictedFirst());
        List<Integer> conflictedOptionsIds = new ArrayList<>();
        for (OptionDto optionDto : conflictedOptions) {
            conflictedOptionsIds.add(optionDto.getId());
        }
        return conflictedOptionsIds;
    }

    @RequestMapping(value = "/options/dependent/{optionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getDependentOptions(@PathVariable(name = "optionId") Integer optionId) {
        List<OptionDto> dependentOptions = new ArrayList<>(optionService.getOption(optionId).getDependentFirst());
        List<Integer> dependentOptionsIds = new ArrayList<>();
        for (OptionDto optionDto : dependentOptions) {
            dependentOptionsIds.add(optionDto.getId());
        }
        return dependentOptionsIds;
    }
}
