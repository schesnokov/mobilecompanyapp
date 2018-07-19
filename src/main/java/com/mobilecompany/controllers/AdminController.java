package com.mobilecompany.controllers;

import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String tariffs(Model model) {
        model.addAttribute("customerList", userService.getAllUsers());
        return "/adminPanel";
    }
}
