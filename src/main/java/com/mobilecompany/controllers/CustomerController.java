package com.mobilecompany.controllers;

import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffsService;
import com.mobilecompany.services.api.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private UsersService usersService;
    private TariffsService tariffsService;
    private OptionService optionService;

    @Autowired
    public CustomerController(UsersService usersService, TariffsService tariffsService, OptionService optionService) {
        this.usersService = usersService;
        this.tariffsService = tariffsService;
        this.optionService = optionService;
    }

    @RequestMapping(value = "/customerPage", method = RequestMethod.GET)
    public String customers(Model model) {
        model.addAttribute("customersList", usersService.getAllUsers());
        return "/customerPage";
    }

    @RequestMapping(value = "/admin/editCustomer/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("tariffList", tariffsService.getAllTariffs());
        model.addAttribute("customer", usersService.getEntity(id));
        model.addAttribute("optionsList", optionService.getAllOptions());
        return "/adminEditCustomer";
    }

}
