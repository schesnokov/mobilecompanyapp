package com.mobilecompany.controllers;

import com.mobilecompany.services.api.TariffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TariffController {

    private static Logger LOGGER = LoggerFactory.getLogger(TariffController.class);

    private TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(value = "/tariffs", method = RequestMethod.GET)
    public String tariffs(Model model) {
        LOGGER.info("Returning page with all tariffs");
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        return "/tariffs";
    }
}
