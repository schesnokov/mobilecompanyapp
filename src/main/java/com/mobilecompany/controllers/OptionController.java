package com.mobilecompany.controllers;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;
import com.mobilecompany.services.api.OptionService;
import com.mobilecompany.services.api.TariffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * The type Option controller.
 */
@Controller
public class OptionController {

    private static Logger LOGGER = LoggerFactory.getLogger(OptionController.class);

    private OptionService optionService;
    private TariffService tariffService;

    /**
     * Instantiates a new Option controller.
     *
     * @param optionService the option service
     * @param tariffService the tariff service
     */
    @Autowired
    public OptionController(OptionService optionService, TariffService tariffService) {
        this.optionService = optionService;
        this.tariffService = tariffService;
    }

    /**
     * Options string.
     *
     * @param model the model
     * @return Options Page
     */
    @RequestMapping(value = "/optionsPage", method = RequestMethod.GET)
    public String options(Model model) {
        LOGGER.info("Returning page with all options");
        model.addAttribute("optionsList", optionService.getAllOptions());
        return "/options";
    }

    /**
     * Add option.
     *
     * @param newOption the new option
     * @return Admin Panel Page
     */
    @RequestMapping(value = "/addOption", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("newOption") NewOptionHelper newOption) {
        LOGGER.info("Adding new option");
        optionService.addOption(newOption);
        return "redirect: /adminPanel";
    }

    /**
     * Delete option string.
     *
     * @param optionId the option id
     * @param model    the model
     * @return All Options Page
     */
    @RequestMapping(value = "/admin/deleteOption/{optionId}", method = RequestMethod.GET)
    public String deleteOption(@PathVariable (name = "optionId") Integer optionId, Model model) {
        LOGGER.info("Blocking option with id {}", optionId);
        optionService.changeOptionStatus(optionId);
        model.addAttribute("optionsList", optionService.getAllOptions());
        //tariffService.sendUpdateMessageToJmsServer();
        return "/options";
    }

    /**
     * Add options to Tariff submit.
     *
     * @param contractChanges the contract changes
     * @param tariffId        the tariff id
     * @return Admin Panel
     */
    @RequestMapping(value = "/addOptionsSubmit/{tariffId}", method = RequestMethod.POST)
    public String addOptionsSubmit(@ModelAttribute(name = "options") ContractChanges contractChanges,
                                   @PathVariable(name = "tariffId") Integer tariffId) {
        LOGGER.info("Adding available options to tariff with id {}", tariffId);
        TariffDto tariffDto = optionService.addAvailableOptions(tariffId, contractChanges);
        tariffService.update(tariffDto);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    /**
     * Delete options from Tariff submit.
     *
     * @param contractChanges the contract changes
     * @param tariffId        the tariff id
     * @return Admin Panel
     */
    @RequestMapping(value = "/deleteOptionsSubmit/{tariffId}", method = RequestMethod.POST)
    public String deleteOptionsSubmit(@ModelAttribute (name = "options") ContractChanges contractChanges,
                                        @PathVariable (name = "tariffId") Integer tariffId) {
        LOGGER.info("Deleting available options from tariff with id {}", tariffId);
        TariffDto tariffDto = optionService.deleteAvailableOptions(tariffId, contractChanges);
        tariffService.update(tariffDto);
        //tariffService.sendUpdateMessageToJmsServer();
        return "redirect: /adminPanel";
    }

    /**
     * Gets All options for tariff.
     *
     * @param tariffId the tariff id
     * @return the options by tariff
     */
    @RequestMapping(value = "/options/{tariffId}", method = RequestMethod.GET)
    @ResponseBody
    public Set<OptionDto> getOptionsByTariff(@PathVariable(name = "tariffId") Integer tariffId) {
        LOGGER.info("Getting all available options for tariff with id {}", tariffId);
        return tariffService.getTariff(tariffId).getAvailableOptions();
    }

    /**
     * Gets conflicted options.
     *
     * @param optionId the option id
     * @return the conflicted options
     */
    @RequestMapping(value = "/options/conflict/{optionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getConflictedOptions(@PathVariable(name = "optionId") Integer optionId) {
        LOGGER.info("Getting conflicted options for option with id {}", optionId);
        return optionService.getConflictedOptions(optionId);
    }

    /**
     * Gets dependent options.
     *
     * @param optionId the option id
     * @return the dependent options
     */
    @RequestMapping(value = "/options/dependent/{optionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getDependentOptions(@PathVariable(name = "optionId") Integer optionId) {
        LOGGER.info("Getting dependent options for option with id {}", optionId);
        return optionService.getDependentOptions(optionId);
    }
}
