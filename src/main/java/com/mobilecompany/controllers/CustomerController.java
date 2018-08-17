package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.NewContractHelper;
import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CustomerController {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;
    private ContractService contractService;

    @Autowired
    public CustomerController(UserService userService, TariffService tariffService, OptionService optionService, ContractService contractService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.contractService = contractService;
    }

    @RequestMapping(value = "/customerPage", method = RequestMethod.GET)
    public String customers(Model model) {
        LOGGER.info("Returning page with all customers");
        model.addAttribute("customersList", userService.getAllUsers());
        return "/customerPage";
    }

    @RequestMapping(value = "/admin/editCustomer/{customerId}")
    public String editCustomer(@PathVariable("customerId") Integer customerId, Model model) {
        LOGGER.info("Returning page for customer edit with id {}", customerId);
        UserDto customer = userService.getUser(customerId);
        List<TariffDto> tariffList = tariffService.getAllTariffs();
        Set<ContractDto> contracts = customer.getContracts();
        model.addAttribute("customer", customer);
        model.addAttribute("contractList", contracts);
        model.addAttribute("newContract", new NewContractHelper());
        model.addAttribute("tariffList", tariffList);
        model.addAttribute("availableOptions", tariffList.get(0).getAvailableOptions());
        return "/account";
    }

    @RequestMapping(value = "/customer/addContract/{customerId}", method = RequestMethod.POST)
    public String addNewContract(@PathVariable(name = "customerId") Integer customerId,
                                 @ModelAttribute("newContract") NewContractHelper newContract, Model model){
        try {
            contractService.getContractByPhone(newContract.getNumber());
            UserDto customer = userService.getUser(customerId);
            List<TariffDto> tariffList = tariffService.getAllTariffs();
            Set<ContractDto> contracts = customer.getContracts();
            model.addAttribute("phoneError", "There is another contract with this phone number");
            model.addAttribute("customer", customer);
            model.addAttribute("contractList", contracts);
            model.addAttribute("newContract", new NewContractHelper());
            model.addAttribute("tariffList", tariffList);
            model.addAttribute("availableOptions", tariffList.get(0).getAvailableOptions());
            return "/account";
        } catch (NoResultException e) {
            LOGGER.info("New contract creation started");
            UserDto customer = userService.getUser(customerId);
            ContractDto contract = new ContractDto();
            contract.setNumber(newContract.getNumber());
            contract.setBalance(newContract.getBalance());
            contract.setTariffDto(tariffService.getTariff(newContract.getTariffId()));
            Set<OptionDto> optionDtos = new HashSet<>();
            for (Integer optionId : newContract.getOptionsIds()) {
                optionDtos.add(optionService.getOption(optionId));
            }
            contract.setSelectedOptions(optionDtos);
            contract.setUserDto(customer);
            customer.getContracts().add(contract);
            userService.update(customer);
            contractService.create(contract);
            return "redirect: /customerPage";
        }
    }
}
