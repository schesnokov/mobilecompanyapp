package com.mobilecompany.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Login controller.
 */
@Controller
public class LoginController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * Login.
     *
     * @param error  the error
     * @param logout the logout
     * @param model  the model
     * @return page where redirect comes from of login page with error notification of succesfull logout page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
     if (error != null) {
         model.addAttribute("error", "Invalid username and password!");
         LOGGER.info("Invalid username of password input");
     }
     if (logout != null) {
         model.addAttribute("msg", "You've been logged out successfully.");
         LOGGER.info("User logged out");
     }
     return "/login";
    }
}
