package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.NewContractHelper;
import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dto.ContractDto;
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
import java.util.List;
import java.util.Set;

/**
 * The Customer controller.
 */
@Controller
public class CustomerController {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;
    private ContractService contractService;
    private MainValidator mainValidator;

    /**
     * Instantiates a new Customer controller.
     *
     * @param userService     the user service
     * @param tariffService   the tariff service
     * @param optionService   the option service
     * @param contractService the contract service
     * @param mainValidator   the main validator
     */
    @Autowired
    public CustomerController(UserService userService, TariffService tariffService, OptionService optionService,
                              ContractService contractService, MainValidator mainValidator) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.contractService = contractService;
        this.mainValidator = mainValidator;
    }

    /**
     * Customers string.
     *
     * @param model the model
     * @return the All Customers Page
     */
    @RequestMapping(value = "/customerPage", method = RequestMethod.GET)
    public String customers(Model model) {
        LOGGER.info("Returning page with all customers");
        model.addAttribute("customersList", userService.getAllUsers());
        return "/customerPage";
    }

    /**
     * Registration of new User
     *
     * @param userDto       the user dto
     * @param bindingResult the binding result
     * @param model         the model
     * @return the Admin Panel
     */
    @RequestMapping(value = "/adminPanel", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (userDto.getDateOfBirth() == null) {
            modelAddAttributes(model);
            return "/adminPanel";
        }
        mainValidator.validatePassport(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAddAttributes(model);
            return "/adminPanel";
        }
        mainValidator.validateEmail(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAddAttributes(model);
            return "/adminPanel";
        }
        mainValidator.validatePwSet(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAddAttributes(model);
            return "/adminPanel";
        }
        LOGGER.info("Registration of user");
        userService.createUser(userDto);
        return "redirect: /adminPanel";
    }

    /**
     * Edit customer.
     *
     * @param customerId the customer id
     * @param model      the model
     * @return the Account page
     */
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

    /**
     * Find customer by phone.
     *
     * @param request the request with phone
     * @param model   the model
     * @return the Account page or Admin Panel Page if no result found
     */
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
            return "/account";
        } catch (NoResultException e) {
            model.addAttribute("findContractByPhoneError", "No contract with this phone");
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("tariffDto", new TariffDto());
            model.addAttribute("newOption", new NewOptionHelper());
            model.addAttribute("optionsList", optionService.getAllOptions());
            return "/adminPanel";
        }
    }

    /**
     * Add new contract.
     *
     * @param customerId  the customer id
     * @param newContract the new contract
     * @param model       the model
     * @return the Account page if the phone number is not unique or the All Customers Page
     */
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
            contractService.create(newContract, customerId);
            return "redirect: /customerPage";
        }
    }

    private void modelAddAttributes(Model model) {
        model.addAttribute("registrationError", "Incorrect data");
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("newOption", new NewOptionHelper());
        model.addAttribute("optionsList", optionService.getAllOptions());
    }
}
