package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.SecurityService;
import com.mobilecompany.services.api.TariffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@SessionAttributes(value = {"contractDto", "tariffList"})
@Controller
public class ContractController {

    private static Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

    private ContractService contractService;
    private TariffService tariffService;
    private SecurityService securityService;


    @Autowired
    public ContractController(ContractService contractService, TariffService tariffService,
                              SecurityService securityService) {
        this.contractService = contractService;
        this.tariffService = tariffService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/contractPage/{id}", method = RequestMethod.GET)
    public String contract(@PathVariable Integer id, Model model) {
        LOGGER.info("Returning contract page for contract with id {}", id);
        ContractDto contractDto = contractService.getContract(id);
        model.addAttribute("contractDto", contractDto);
        model.addAttribute("contractChanges", new ContractChanges());
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("availableOptions", contractDto.getTariff().getAvailableOptions());
        return "/contractPage";
    }

    @RequestMapping(value = "/changeStatus/{contractId}", method = RequestMethod.POST)
    public String changeStatus(@PathVariable(name = "contractId") Integer contractId, Model model) {
        LOGGER.info("Changing status of contract with id {}", contractId);
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
                               @PathVariable(name = "contractId") Integer contractId,
                               Model model, HttpServletRequest request) {
        LOGGER.info("Change tariff of contract with id {}", contractId);
        ContractChanges contractChanges1 = (ContractChanges) request.getSession().getAttribute("contractChanges");
        contractService.changeTariff(contractChanges1, contractId);
        ContractDto contract = contractService.getContract(contractId);
        model.addAttribute("contractDto", contract);
        model.addAttribute("availableOptions", contract.getTariff().getAvailableOptions());
        request.getSession().setAttribute("bucket", null);
        request.getSession().setAttribute("orderResult", null);
        return "/contractPage";
    }
}
