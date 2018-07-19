package com.mobilecompany.controllers;

import com.mobilecompany.services.api.TariffsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutController {

    private TariffsService tariffsService;

    @Autowired
    public AboutController(TariffsService tariffsService) {
        this.tariffsService = tariffsService;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("tariffList", tariffsService.getAllTariffs());
        return "/about";
    }
}
