package com.mobilecompany.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController {
    private static Logger LOGGER = LoggerFactory.getLogger(AboutController.class);


    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "/404";
    }

/*    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception e) {
        LOGGER.error(request.getRequestURL() + " raised" + e);
        LOGGER.error("Stacktrace:\n");
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", "this is Exception.class");
        return model;
    }*/

    /*@ExceptionHandler(ServiceGenericException.class)
    public ModelAndView handleServiceException(ServiceGenericException sge) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", sge.getMessage());
        return model;
    }*/

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resetLogin() {
        return new ModelAndView("redirect: /");
    }
}
