package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.NewContractHelper;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.SecurityService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.services.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * The Account controller.
 */
@Controller
public class AccountController {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private UserService userService;
    private SecurityService securityService;
    private TariffService tariffService;

    /**
     * Instantiates a new Account controller.
     *
     * @param userService     the user service
     * @param securityService the security service
     * @param tariffService   the tariff service
     */
    @Autowired
    public AccountController(UserService userService, SecurityService securityService, TariffService tariffService) {
        this.securityService = securityService;
        this.userService = userService;
        this.tariffService = tariffService;
    }

    /**
     * Account string.
     *
     * @param model the model
     * @return the Account page
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        LOGGER.info("Returning account page");
        List<TariffDto> tariffList = tariffService.getAllTariffs();
        String userEmail = securityService.findLoggedInEmail();
        model.addAttribute("customer", userService.findByEmail(userEmail));
        model.addAttribute("contractList", userService.findByEmail(userEmail).getContracts());
        model.addAttribute("newContract", new NewContractHelper());
        model.addAttribute("tariffList", tariffList);
        model.addAttribute("availableOptions", tariffList.get(0).getAvailableOptions());
        return "/account";
    }
}
