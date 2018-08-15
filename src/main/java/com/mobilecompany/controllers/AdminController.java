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
import com.mobilecompany.validators.MainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;
    private ContractService contractService;
    private MainValidator mainValidator;

    @Autowired
    public AdminController(UserService userService, TariffService tariffService, OptionService optionService,
                           ContractService contractService, MainValidator mainValidator) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.contractService = contractService;
        this.mainValidator = mainValidator;
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String tariffs(Model model) {
        LOGGER.info("Returning admin panel");
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("newOption", new NewOptionHelper());
        model.addAttribute("optionsList", optionService.getAllOptions());
        return "/adminPanel";
    }

    @RequestMapping(value = "/findByPhone", method = RequestMethod.GET)
    public String findCustomerByPhone(HttpServletRequest request, Model model) {
        try {
            Contract contract = contractService.getContractByPhone(request.getParameter("phone"));
            LOGGER.info("Getting customer by phone number");
            User customer = contract.getUser();
            Set<Contract> contracts = customer.getContracts();
            model.addAttribute("customer", customer);
            model.addAttribute("contractList", contracts);
            model.addAttribute("newContract", new NewContractHelper());
            model.addAttribute("tariffList", tariffService.getAllTariffs());
            model.addAttribute("availableOptions", tariffService.getAllTariffs().get(0).getAvailableOptions());
            return "/adminEditCustomer";
        } catch (NoResultException e) {
            model.addAttribute("findContractByPhoneError", "No contract with this phone");
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("tariffDto", new TariffDto());
            model.addAttribute("newOption", new NewOptionHelper());
            model.addAttribute("optionsList", optionService.getAllOptions());
            return "/adminPanel";
        }
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        mainValidator.validateDateOfBirth(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationError", "Date of birth is incorrect");
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("tariffDto", new TariffDto());
            model.addAttribute("newOption", new NewOptionHelper());
            model.addAttribute("optionsList", optionService.getAllOptions());
            return "/adminPanel";
        }
        mainValidator.validatePassport(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationError", "Passport number is incorrect");
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("tariffDto", new TariffDto());
            model.addAttribute("newOption", new NewOptionHelper());
            model.addAttribute("optionsList", optionService.getAllOptions());
            return "/adminPanel";
        }
        mainValidator.validateEmail(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationError","Email is incorrect");
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("tariffDto", new TariffDto());
            model.addAttribute("newOption", new NewOptionHelper());
            model.addAttribute("optionsList", optionService.getAllOptions());
            return "/adminPanel";
        }
        mainValidator.validatePwSet(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationError", "Invalid password");
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("tariffDto", new TariffDto());
            model.addAttribute("newOption", new NewOptionHelper());
            model.addAttribute("optionsList", optionService.getAllOptions());
            return "/adminPanel";
        }
        LOGGER.info("Registration of user");
        userService.createUser(userDto);
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariffDto") TariffDto tariffDto, BindingResult bindingResult, Model model) {
        LOGGER.info("Adding new tariff {}", tariffDto);
        tariffService.addTariff(tariffDto);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/editTariff/{tariffId}", method = RequestMethod.GET)
    public String editTariff(@PathVariable (name = "tariffId") Integer tariffId, Model model) {
        LOGGER.info("Editing tariff with id {}", tariffId);
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
        LOGGER.info("Deleting available options from tariff with id {}", tariffId);
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        for (Integer optionId : contractChanges.getOptionsIds()) {
            Set<OptionDto> availableOptions = tariffDto.getAvailableOptions();
            OptionDto optionDto = optionService.getOption(optionId);
            availableOptions.remove(optionDto);
            tariffDto.setAvailableOptions(availableOptions);
            tariffService.update(tariffDto);
        }
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/addOptionsSubmit/{tariffId}", method = RequestMethod.POST)
    public String addOptionsSubmit(@ModelAttribute (name = "options") ContractChanges contractChanges,
                                   @PathVariable (name = "tariffId") Integer tariffId) {
        LOGGER.info("Adding available options from tariff with id {}", tariffId);
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        Set<OptionDto> availableOptions = new HashSet<>();
        availableOptions.addAll(tariffDto.getAvailableOptions());
        for (Integer optionId : contractChanges.getOptionsIds2()) {
            availableOptions.add(optionService.getOption(optionId));
        }
        tariffDto.setAvailableOptions(availableOptions);
        tariffService.update(tariffDto);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.POST)
    public String deleteTariff(@PathVariable Integer id) {
        LOGGER.info("Blocking tariff with id {}", id);
        tariffService.changeTariffStatus(id);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("newOption") NewOptionHelper newOption) {
        LOGGER.info("Adding new option");
        OptionDto optionDto = new OptionDto();
        optionDto.setName(newOption.getName());
        optionDto.setDescription(newOption.getDescription());
        optionDto.setPrice(newOption.getPrice());
        optionDto.setConnectionCost(newOption.getConnectionCost());
        Set<OptionDto> dependentOptions = new HashSet<>();
        for (Integer dependentOptionId : newOption.getDependentIds()) {
            dependentOptions.add(optionService.getOption(dependentOptionId));
        }
        Set<OptionDto> conflictedOptions = new HashSet<>();
        for (Integer conflictedOptionId : newOption.getConflictedIds()) {
            conflictedOptions.add(optionService.getOption(conflictedOptionId));
        }
        optionDto.setDependentFirst(dependentOptions);
        optionDto.setConflictedFirst(conflictedOptions);
        optionService.addOption(optionDto);
        return "redirect: /adminPanel";
    }
}
