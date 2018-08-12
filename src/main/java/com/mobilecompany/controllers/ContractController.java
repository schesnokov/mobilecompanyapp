package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SessionAttributes(value = {"contractDto", "tariffList"})
@Controller
public class ContractController {

    private ContractService contractService;
    private TariffService tariffService;
    private OptionService optionService;
    private SecurityService securityService;
    private UserService userService;


    @Autowired
    public ContractController(ContractService contractService, TariffService tariffService, OptionService optionService,
                              SecurityService securityService, UserService userService) {
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.securityService = securityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/contractPage/{id}", method = RequestMethod.GET)
    public String contract(@PathVariable Integer id, Model model) {
        ContractDto contractDto = contractService.getContract(id);
        model.addAttribute("contractDto", contractDto);
        model.addAttribute("contractChanges", new ContractChanges());
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("availableOptions", contractDto.getTariff().getAvailableOptions());
        return "/contractPage";
    }

    @RequestMapping(value = "/changeStatus/{contractId}", method = RequestMethod.POST)
    public String changeStatus(@PathVariable(name = "contractId") Integer contractId, Model model) {
        if (securityService.findLoggedInEmail().equals("chesnokov.sergei@gmail.com")) {
            contractService.changeStatusByAdmin(contractId);
        } else {
            contractService.changeStatus(contractId);
        }
        ContractDto contract = contractService.getContract(contractId);
        model.addAttribute("contractDto", contract);
        model.addAttribute("availableOptions", contract.getTariff().getAvailableOptions());
        model.addAttribute("contractChanges", new ContractChanges());
        return "/contractPage";
    }

    @RequestMapping(value = "/changeTariff/{contractId}", method = RequestMethod.POST)
    public String changeTariff(@ModelAttribute("contractChanges") ContractChanges contractChanges,
                               @PathVariable(name = "contractId") Integer contractId, Model model, HttpServletRequest request) {
        ContractChanges contractChanges1 = (ContractChanges) request.getSession().getAttribute("contractChanges");
        contractService.changeTariff(contractChanges1, contractId);
        ContractDto contract = contractService.getContract(contractId);
        model.addAttribute("contractDto", contract);
        model.addAttribute("availableOptions", contract.getTariff().getAvailableOptions());
        request.getSession().setAttribute("bucket", null);
        request.getSession().setAttribute("orderResult", null);
        return "/contractPage";
    }

    @RequestMapping(value = "/options/{tariffId}", method = RequestMethod.GET)
    @ResponseBody
    public Set<OptionDto> getOptionsByTariff(@PathVariable(name = "tariffId") Integer tariffId) {
        Set<OptionDto> availableOptions;
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
