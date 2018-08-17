package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import com.mobilecompany.validators.MainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TariffController {

    private static Logger LOGGER = LoggerFactory.getLogger(TariffController.class);

    private TariffService tariffService;
    private OptionService optionService;
    private MainValidator mainValidator;

    @Autowired
    public TariffController(TariffService tariffService, OptionService optionService,
                            MainValidator mainValidator) {
        this.tariffService = tariffService;
        this.optionService = optionService;
        this.mainValidator = mainValidator;
    }

    @RequestMapping(value = "/tariffs", method = RequestMethod.GET)
    public String tariffs(Model model) {
        LOGGER.info("Returning page with all tariffs");
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        return "/tariffs";
    }

    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariffDto") TariffDto tariffDto) {
        LOGGER.info("Adding new tariff {}", tariffDto);
        tariffService.addTariff(tariffDto);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.POST)
    public String deleteTariff(@PathVariable Integer id) {
        LOGGER.info("Blocking tariff with id {}", id);
        tariffService.changeTariffStatus(id);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: ../tariffs";
    }

    @RequestMapping(value = "/editTariff/{tariffId}", method = RequestMethod.GET)
    public String editTariff(@PathVariable(name = "tariffId") Integer tariffId, Model model) {
        LOGGER.info("Editing tariff with id {}", tariffId);
        List<OptionDto> available = new ArrayList<>(tariffService.getTariff(tariffId).getAvailableOptions());
        List<OptionDto> allOptions = optionService.getAllOptions();
        allOptions.removeAll(available);
        TariffDto tariffDto = tariffService.getTariff(tariffId);
        model.addAttribute("tariff", tariffDto);
        model.addAttribute("optionsList", tariffDto.getAvailableOptions());
        model.addAttribute("options", new ContractChanges());
        model.addAttribute("allOptionsList", allOptions);
        return "/adminEditTariff";
    }
}
