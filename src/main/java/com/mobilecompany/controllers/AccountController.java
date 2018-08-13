package com.mobilecompany.controllers;

import com.mobilecompany.services.api.SecurityService;
import com.mobilecompany.services.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public AccountController(UserService userService, SecurityService securityService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        LOGGER.info("Returning account page");
        String userEmail = securityService.findLoggedInEmail();
        model.addAttribute("customer", userService.findByEmail(userEmail));
        model.addAttribute("contractList", userService.findByEmail(userEmail).getContracts());
        return "/account";
    }
}
