package com.mobilecompany.controllers;

import com.mobilecompany.entities.Tariffs;
import com.mobilecompany.services.api.TariffsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class TariffRestController {
    private TariffsService tariffsService;

    @Autowired
    public  TariffRestController(TariffsService tariffsService) {
        this.tariffsService = tariffsService;
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.GET)
    public String deleteTariff(@PathVariable Integer id) {
        tariffsService.deleteTariff(id);
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.GET)
    public String addTariff(@RequestParam("tariffName") String tariffName,
                            @RequestParam("tariffDescription") String tariffDescription,
                            @RequestParam("tariffPrice") BigDecimal tariffPrice) {
        Tariffs tariff = new Tariffs();
        tariff.setName(tariffName);
        tariff.setDescription(tariffDescription);
        tariff.setPrice(tariffPrice);
        tariffsService.addTariff(tariff);
        return "redirect: /tariffs";
    }
}
