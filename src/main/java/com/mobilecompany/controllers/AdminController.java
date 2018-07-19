package com.mobilecompany.controllers;

import com.mobilecompany.services.api.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    private UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String tariffs(Model model) {
        model.addAttribute("customerList", usersService.getAllUsers());
        return "/adminPanel";
    }
}
