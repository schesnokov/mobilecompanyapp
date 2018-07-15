package com.mobilecompany.controllers;

import com.mobilecompany.entities.Users;
import com.mobilecompany.services.api.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class AccountRestController {

    private UsersService usersService;

    @Autowired
    public  AccountRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@RequestParam("firstName") String firstName,
                          @RequestParam("secondName") String secondName,
                          @RequestParam("dateOfBirth") Date birthDate,
                          @RequestParam("passport") String passport,
                          @RequestParam("adress") String adress,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password) {
        Users user = new Users();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setDateOfBirth(birthDate);
        user.setPassportNumber(passport);
        user.setAdress(adress);
        user.setEmail(email);
        user.setPassword(password);
        user.setIsBlocked(0);
        usersService.createUser(user);
        return "redirect: /adminPanel";
    }
}
