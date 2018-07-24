package com.mobilecompany.controllers;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
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
public class AdminController {

    private UserService userService;
    private TariffService tariffService;
    private OptionService optionService;

    @Autowired
    public AdminController(UserService userService, TariffService tariffService, OptionService optionService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String tariffs(Model model) {
        model.addAttribute("customerList", userService.getAllUsers());
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("optionDto", new OptionDto());
        return "/adminPanel";
    }

    @RequestMapping(value = "/adminPanel", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userDto") UserDto userDto) {
        userService.createUser(userDto);
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariffDto") TariffDto tariffDto) {
        tariffService.addTariff(tariffDto);
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.POST)
    public String deleteTariff(@PathVariable Integer id) {
        tariffService.deleteTariff(id);
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("optionDto") OptionDto optionDto) {
        optionService.addOption(optionDto);
        return "redirect: /adminPanel";
    }
}
