package com.mobilecompany.controllers;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.Contract;
import com.mobilecompany.entities.User;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;
    private ContractService contractService;

    @Autowired
    public AdminController(UserService userService, TariffService tariffService, OptionService optionService, ContractService contractService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.contractService = contractService;
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String tariffs(Model model) {
        model.addAttribute("customerList", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("optionDto", new OptionDto());
        return "/adminPanel";
    }

    @RequestMapping(value = "/findByPhone", method = RequestMethod.GET)
    public String findCustomerByPhone(HttpServletRequest request, Model model) {
        Contract contract = contractService.getContractByPhone(request.getParameter("phone"));
        User customer = contract.getUser();
        Set<Contract> contracts = customer.getContracts();
        model.addAttribute("customer", customer);
        model.addAttribute("contractList", contracts);
        return "/adminEditCustomer";
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userDto") UserDto userDto) {
        userService.createUser(userDto);
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariffDto") TariffDto tariffDto) {
        tariffService.addTariff(tariffDto);
        tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.POST)
    public String deleteTariff(@PathVariable Integer id) {
        tariffService.deleteTariff(id);
        tariffService.sendUpdateMessageToJmsServer();
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("optionDto") OptionDto optionDto) {
        optionService.addOption(optionDto);
        return "redirect: /adminPanel";
    }
}
