package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewContractHelper;
import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.Contract;
import com.mobilecompany.entities.User;
import com.mobilecompany.services.api.ContractService;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;
    private ContractService contractService;
    private ModelMapper mapper;

    @Autowired
    public AdminController(UserService userService, TariffService tariffService, OptionService optionService, ContractService contractService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.contractService = contractService;
        this.mapper = new ModelMapper();
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String tariffs(Model model) {
        model.addAttribute("customerList", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("newOption", new NewOptionHelper());
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        return "/adminPanel";
    }

    @RequestMapping(value = "/findByPhone", method = RequestMethod.GET)
    public String findCustomerByPhone(HttpServletRequest request, Model model) {
        Contract contract = contractService.getContractByPhone(request.getParameter("phone"));
        User customer = contract.getUser();
        Set<Contract> contracts = customer.getContracts();
        model.addAttribute("customer", customer);
        model.addAttribute("contractList", contracts);
        model.addAttribute("newContract", new NewContractHelper());
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("availableOptions", tariffService.getAllTariffs().get(0).getAvailableOptions());
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
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/editTariff/{tariffId}", method = RequestMethod.GET)
    public String editTariff(@PathVariable (name = "tariffId") Integer tariffId, Model model) {
        model.addAttribute("tariff", tariffService.getTariff(tariffId));
        List<OptionDto> available = new ArrayList<>(tariffService.getTariff(tariffId).getAvailableOptions());
        List<OptionDto> allOptions = optionService.getAllOptions();
        allOptions.removeAll(available);
        model.addAttribute("optionsList", tariffService.getTariff(tariffId).getAvailableOptions());
        model.addAttribute("options", new ContractChanges());
        model.addAttribute("allOptionsList", allOptions);
        return "/adminEditTariff";
    }

    @RequestMapping(value = "/deleteOptionsSubmit/{tariffId}", method = RequestMethod.POST)
    public String   deleteOptionsSubmit(@ModelAttribute (name = "options") ContractChanges contractChanges,
                                   @PathVariable (name = "tariffId") Integer tariffId) {
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        for (Integer optionId : contractChanges.getOptionsIds()) {
            Set<OptionDto> availableOptions = tariffDto.getAvailableOptions();
            OptionDto optionDto = optionService.getOption(optionId);
            availableOptions.remove(optionDto);
            tariffDto.setAvailableOptions(availableOptions);
            tariffService.update(tariffDto);
            //tariffService.sendUpdateMessageToJmsServer();
        }
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/addOptionsSubmit/{tariffId}", method = RequestMethod.POST)
    public String addOptionsSubmit(@ModelAttribute (name = "options") ContractChanges contractChanges,
                                   @PathVariable (name = "tariffId") Integer tariffId) {
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        for (Integer optionId : contractChanges.getOptionsIds2()) {
            tariffDto.getAvailableOptions().add(optionService.getOption(optionId));
            tariffService.update(tariffDto);
            //tariffService.sendUpdateMessageToJmsServer();
        }
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.POST)
    public String deleteTariff(@PathVariable Integer id) {
        tariffService.deleteTariff(id);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("newOption") NewOptionHelper newOption) {
        OptionDto optionDto = new OptionDto();
        optionDto.setName(newOption.getName());
        optionDto.setDescription(newOption.getDescription());
        optionDto.setPrice(newOption.getPrice());
        optionDto.setConnectionCost(newOption.getConnectionCost());
        /*Set<TariffDto> tariffDtos = new HashSet<>();
        for (Integer tariffId : newOption.getCompatibleTariffsIds()) {
            tariffDtos.add(tariffService.getTariff(tariffId));
            *//*TariffDto tariffDto = tariffService.getTariff(tariffId);
            tariffDto.getAvailableOptions().add(optionDto);
            tariffService.update(tariffDto);*//*
            //tariffService.sendUpdateMessageToJmsServer();
        }
        optionDto.setTariffs(tariffDtos);*/
        optionService.addOption(optionDto);
        return "redirect: /adminPanel";
    }
}
