package com.mobilecompany.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle() {
        return "/404";
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView resetLogin() {
        return new ModelAndView("redirect: /");
    }

    @ExceptionHandler(Exception.class)
    public String pyatsotka(){
        //todo 500
        return "/404";
    }

}
