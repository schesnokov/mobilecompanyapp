package com.mobilecompany.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Index controller.
 */
@Controller
public class IndexController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    /**
     * Index.
     *
     * @return the Index Page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        LOGGER.info("Returning index page");
        return "/index";
    }
}
