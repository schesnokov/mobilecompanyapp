package com.mobilecompany.controllers;

import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.services.api.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


@Controller
public class OptionRestController {

    private OptionService optionService;

    @Autowired
    public OptionRestController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping(value = "/addOption", method = RequestMethod.GET)
    public String addOption(@RequestParam("optionName") String optionName,
                            @RequestParam("optionDescription") String optionDescription,
                            @RequestParam("optionPrice") BigDecimal optionPrice,
                            @RequestParam("connectionCost") BigDecimal connectionCost) {
        OptionDto option = new OptionDto();
        option.setName(optionName);
        option.setPrice(optionPrice);
        option.setDescription(optionDescription);
        option.setConnectionCost(connectionCost);
        optionService.addOption(option);
        return "redirect: /adminPanel";
    }
}
