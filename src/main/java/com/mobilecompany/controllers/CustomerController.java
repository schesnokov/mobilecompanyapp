package com.mobilecompany.controllers;

import com.mobilecompany.dto.ContractDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.entities.User;
import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class CustomerController {

    private UserService userService;

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/customerPage", method = RequestMethod.GET)
    public String customers(Model model) {
        model.addAttribute("customersList", userService.getAllUsers());
        return "/customerPage";
    }

    @RequestMapping(value = "/admin/editCustomer/{customerId}")
    public String editCustomer(@PathVariable("customerId") Integer customerId, Model model) {
        UserDto customer = userService.getUser(customerId);
        Set<ContractDto> contracts = customer.getContracts();
        model.addAttribute("customer", customer);
        model.addAttribute("contractList", contracts);
        return "/adminEditCustomer";
    }

    @RequestMapping(value = "/saveEditedCustomer")
    public String saveEditedCustomer(@ModelAttribute("customer") User user) {
        return "/customerPage";
    }
}
