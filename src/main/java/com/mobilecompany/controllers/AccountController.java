package com.mobilecompany.controllers;

import com.mobilecompany.services.api.SecurityService;
import com.mobilecompany.services.api.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    private UsersService usersService;
    private SecurityService securityService;

    @Autowired
    public AccountController(UsersService usersService, SecurityService securityService) {
        this.securityService = securityService;
        this.usersService = usersService;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        String userEmail = securityService.findLoggedInEmail();
        model.addAttribute("customer", usersService.findByEmail(userEmail));
        return "/account";
    }
}
