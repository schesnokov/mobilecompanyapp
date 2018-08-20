package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.dto.UserDto;
import com.mobilecompany.services.api.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Admin controller.
 */
@Controller
public class AdminController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private OptionService optionService;

    /**
     * Instantiates a new Admin controller.
     *
     * @param optionService the option service
     */
    @Autowired
    public AdminController(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * Admin panel string.
     *
     * @param model the model
     * @return the Administration Panel page
     */
    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String adminPanel(Model model) {
        LOGGER.info("Returning admin panel");
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("tariffDto", new TariffDto());
        model.addAttribute("newOption", new NewOptionHelper());
        model.addAttribute("optionsList", optionService.getAllOptions());
        return "/adminPanel";
    }
}
