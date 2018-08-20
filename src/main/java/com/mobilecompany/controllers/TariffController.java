package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
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

/**
 * The type Tariff controller.
 */
@Controller
public class TariffController {

    private static Logger LOGGER = LoggerFactory.getLogger(TariffController.class);

    private TariffService tariffService;
    private OptionService optionService;

    /**
     * Instantiates a new Tariff controller.
     *
     * @param tariffService the tariff service
     * @param optionService the option service
     */
    @Autowired
    public TariffController(TariffService tariffService, OptionService optionService) {
        this.tariffService = tariffService;
        this.optionService = optionService;
    }

    /**
     * Tariffs string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/tariffs", method = RequestMethod.GET)
    public String tariffs(Model model) {
        LOGGER.info("Returning page with all tariffs");
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        return "/tariffs";
    }

    /**
     * Add tariff string.
     *
     * @param tariffDto the tariff dto
     * @return the string
     */
    @RequestMapping(value = "/addTariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariffDto") TariffDto tariffDto) {
        LOGGER.info("Adding new tariff {}", tariffDto);
        tariffService.addTariff(tariffDto);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    /**
     * Delete tariff string.
     *
     * @param id the id
     * @return the string
     */
    @RequestMapping(value = "/deleteTariff/{id}", method = RequestMethod.POST)
    public String deleteTariff(@PathVariable Integer id) {
        LOGGER.info("Blocking tariff with id {}", id);
        tariffService.changeTariffStatus(id);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: ../tariffs";
    }

    /**
     * Edit tariff string.
     *
     * @param tariffId the tariff id
     * @param model    the model
     * @return the string
     */
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
