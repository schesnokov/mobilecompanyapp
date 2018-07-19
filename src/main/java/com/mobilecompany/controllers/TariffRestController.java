package com.mobilecompany.controllers;

import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class TariffRestController {
    private TariffService tariffService;

    @Autowired
    public  TariffRestController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.GET)
    public String deleteTariff(@PathVariable Integer id) {
        tariffService.deleteTariff(id);
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.GET)
    public String addTariff(@RequestParam("tariffName") String tariffName,
                            @RequestParam("tariffDescription") String tariffDescription,
                            @RequestParam("tariffPrice") BigDecimal tariffPrice) {
        TariffDto tariff = new TariffDto();
        tariff.setName(tariffName);
        tariff.setDescription(tariffDescription);
        tariff.setPrice(tariffPrice);
        tariffService.addTariff(tariff);
        return "redirect: /tariffs";
    }
}
