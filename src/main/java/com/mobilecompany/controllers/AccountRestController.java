package com.mobilecompany.controllers;

import com.mobilecompany.dto.UserDto;
import com.mobilecompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class AccountRestController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public  AccountRestController(UserService userService) {
        this.userService = userService;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@RequestParam("firstName") String firstName,
                          @RequestParam("secondName") String secondName,
                          @RequestParam("dateOfBirth") Date birthDate,
                          @RequestParam("passport") String passport,
                          @RequestParam("adress") String adress,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password) {
        UserDto user = new UserDto();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setDateOfBirth(birthDate);
        user.setPassportNumber(passport);
        user.setAdress(adress);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsBlocked(0);
        userService.createUser(user);
        return "redirect: /adminPanel";
    }
}
