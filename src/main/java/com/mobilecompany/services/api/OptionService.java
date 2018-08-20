package com.mobilecompany.services.api;

import com.mobilecompany.controllers.model.ContractChanges;
import com.mobilecompany.controllers.model.NewOptionHelper;
import com.mobilecompany.dto.OptionDto;
import com.mobilecompany.dto.TariffDto;

import java.util.List;

/**
 * The interface Option service.
 */
public interface OptionService {
    /**
     * Gets option.
     *
     * @param id the id
     * @return the option
     */
    OptionDto getOption(Integer id);

    /**
     * Gets all options.
     *
     * @return the all options
     */
    List<OptionDto> getAllOptions();

    /**
     * Add option.
     *
     * @param newOption the new option
     */
    void addOption(NewOptionHelper newOption);

    /**
     * Change option status.
     *
     * @param optionId the option id
     */
    void changeOptionStatus(Integer optionId);

    /**
     * Add available options tariff dto.
     *
     * @param tariffId        the tariff id
     * @param contractChanges the contract changes
     * @return the tariff dto
     */
    TariffDto addAvailableOptions(Integer tariffId, ContractChanges contractChanges);

    /**
     * Delete available options tariff dto.
     *
     * @param tariffId        the tariff id
     * @param contractChanges the contract changes
     * @return the tariff dto
     */
    TariffDto deleteAvailableOptions(Integer tariffId, ContractChanges contractChanges);

    /**
     * Gets conflicted options.
     *
     * @param optionId the option id
     * @return the conflicted options
     */
    List<Integer> getConflictedOptions(Integer optionId);

    /**
     * Gets dependent options.
     *
     * @param optionId the option id
     * @return the dependent options
     */
    List<Integer> getDependentOptions(Integer optionId);
}
