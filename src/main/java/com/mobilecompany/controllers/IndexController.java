package com.mobilecompany.controllers;

import com.mobilecompany.services.api.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private OptionService optionService;

    @Autowired
    public IndexController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("msg", optionService.getEntity(1).getName());
        return "index";
    }
}
