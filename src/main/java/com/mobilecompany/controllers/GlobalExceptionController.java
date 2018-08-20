package com.mobilecompany.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * The Global exception controller.
 */
@ControllerAdvice
public class GlobalExceptionController {

    /**
     * Handle 404 error.
     *
     * @return the custom 404 page
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "/404";
    }

    /**
     * Handle null pointer exception
     *
     * @return the Index page
     */
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resetLogin() {
        return new ModelAndView("redirect: /");
    }

    /**
     * Handle 500 error.
     *
     * @param ex    the ex
     * @param model the model
     * @return the custom 500 page
     */
    @ExceptionHandler(Exception.class)
    public String pyatsotka(Exception ex, Model model){
        model.addAttribute("exception", ex);
        return "/500";
    }
}
