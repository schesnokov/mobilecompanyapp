package com.mobilecompany.controllers;

import com.mobilecompany.entities.User;
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

@Controller
public class CustomerController {

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;

    @Autowired
    public CustomerController(UserService userService, TariffService tariffService, OptionService optionService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    @RequestMapping(value = "/customerPage", method = RequestMethod.GET)
    public String customers(Model model) {
        model.addAttribute("customersList", userService.getAllUsers());
        return "/customerPage";
    }

    @RequestMapping(value = "/admin/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        model.addAttribute("customer", userService.getUser(id));
        model.addAttribute("optionsList", optionService.getAllOptions());
        return "/adminEditCustomer";
    }

    @RequestMapping(value = "/saveEditedCustomer")
    public String saveEditedCustomer(@ModelAttribute("customer") User user){
        return "/customerPage";
    }
}
