package com.mobilecompany.controllers;

import com.mobilecompany.services.api.SecurityService;
import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public AccountController(UserService userService, SecurityService securityService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        String userEmail = securityService.findLoggedInEmail();
        model.addAttribute("customer", userService.findByEmail(userEmail));
        return "/account";
    }
}
