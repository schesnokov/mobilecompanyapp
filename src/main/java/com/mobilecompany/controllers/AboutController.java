package com.mobilecompany.controllers;

import com.mobilecompany.services.api.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutController {

    private TariffService tariffService;

    @Autowired
    public AboutController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        return "/about";
    }
}
