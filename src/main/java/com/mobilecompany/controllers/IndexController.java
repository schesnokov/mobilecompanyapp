package com.mobilecompany.controllers;

import com.mobilecompany.dao.OptionDao;
import com.mobilecompany.entities.Option;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        OptionDao optionDao = new OptionDao();
        Option option = optionDao.read(1);
        model.addAttribute("msg", option.getName());
        return "index";
    }
}
